package fr.polytech.pop3.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.polytech.pop3.server.states.ClosedState;
import fr.polytech.pop3.server.states.State;

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
		((Thread) this.pop3TimerObserver).start();

		try {
			final BufferedInputStream inputStream = new BufferedInputStream(this.socket.getInputStream());
			final BufferedOutputStream outputStream = new BufferedOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "An unexpected exception occured.", e);
		}
	}

	@Override
	public void notifyAutoLogout() {
		try {
			this.socket.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "An unexpected exception occured while trying to close the socket.", e);
		}
	}
}