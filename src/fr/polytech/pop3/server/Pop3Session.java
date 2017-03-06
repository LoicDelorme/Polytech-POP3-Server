package fr.polytech.pop3.server;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.polytech.pop3.server.states.ClosedState;
import fr.polytech.pop3.server.states.State;
import fr.polytech.pop3.server.states.results.StateResult;

/**
 * This class represents a POP 3 session.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Pop3Session implements Runnable, Pop3TimerObservable {

	/**
	 * The logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(Pop3Session.class.getName());

	/**
	 * The client socket.
	 */
	private final Socket socket;

	/**
	 * The POP 3 timer observer.
	 */
	private final Pop3TimerObserver pop3TimerObserver;

	/**
	 * The current state.
	 */
	private State currentState;

	/**
	 * Create a POP 3 session.
	 * 
	 * @param socket
	 *            The client socket.
	 * @param connectionTimeout
	 *            The connection timeout.
	 */
	public Pop3Session(Socket socket, int connectionTimeout) {
		this.socket = socket;
		this.pop3TimerObserver = new Pop3Stopwatch(connectionTimeout, this);
		this.currentState = new ClosedState();
	}

	@Override
	public void run() {
		try {
			final BufferedReader inputStream = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			final DataOutput outputStream = new DataOutputStream(this.socket.getOutputStream());

			((Thread) this.pop3TimerObserver).start();

			String inputCommand = "";
			StateResult stateResult = null;
			while (true) {
				this.pop3TimerObserver.notifyActivity();
				stateResult = this.currentState.runCommand(inputCommand.trim());

				this.currentState = stateResult.getNextState();
				outputStream.writeBytes(stateResult.getMessage() + "\r\n");

				if (this.currentState == null) {
					break;
				}

				inputCommand = inputStream.readLine();
			}

			this.socket.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "[SERVER_THREAD] An unexpected exception occured", e);
		}
	}

	@Override
	public void notifyAutoLogout() {
		try {
			this.socket.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "[SERVER_THREAD] An unexpected exception occured while trying to close the socket", e);
		}
	}
}