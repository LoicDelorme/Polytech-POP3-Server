package fr.polytech.pop3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents a POP 3 server.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Pop3Server implements Runnable {

	/**
	 * The server port.
	 */
	private static final int SERVER_PORT = 100;

	/**
	 * The server queue length.
	 */
	private static final int SERVER_QUEUE_LENGHT = 50;

	/**
	 * The logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(Pop3Server.class.getName());

	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT, SERVER_QUEUE_LENGHT)) {
			while (true) {
				Socket client = serverSocket.accept();

				Thread clientThread = new Thread(new Pop3Session(client));
				clientThread.start();
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Unabled to create POP 3 server socket on port: " + SERVER_PORT);
		}
	}

	/**
	 * The entry of the application.
	 * 
	 * @param args
	 *            Some arguments.
	 */
	public static void main(String[] args) {
		Thread serverThread = new Thread(new Pop3Server());
		serverThread.start();
	}
}