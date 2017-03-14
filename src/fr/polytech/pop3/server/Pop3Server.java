package fr.polytech.pop3.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

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
	private static final int SERVER_PORT = 2017;

	/**
	 * The server queue length.
	 */
	private static final int SERVER_QUEUE_LENGHT = 10;

	/**
	 * The server autologout delay.
	 */
	private static final int SERVER_AUTOLOGOUT_DELAY = 10 * 60; // 10 minutes in seconds.

	/**
	 * The logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(Pop3Server.class.getName());

	@Override
	public void run() {
		final SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

		try (final SSLServerSocket serverSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(SERVER_PORT, SERVER_QUEUE_LENGHT)) {
			serverSocket.setEnabledCipherSuites(Arrays.stream(sslServerSocketFactory.getSupportedCipherSuites()).filter(cipher -> cipher.contains("anon")).toArray(size -> new String[size]));

			Socket client = null;
			while (true) {
				client = serverSocket.accept();

				final Thread clientThread = new Thread(new Pop3Session(client, SERVER_AUTOLOGOUT_DELAY));
				clientThread.start();

				LOGGER.log(Level.INFO, String.format("[SERVER] New client (address: %s ; port: %s)", client.getInetAddress().getHostName(), client.getPort()));
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "[SERVER] Unabled to create POP 3 server socket on port: " + SERVER_PORT, e);
		}
	}

	/**
	 * The entry of the application.
	 * 
	 * @param args
	 *            Some arguments.
	 */
	public static void main(String[] args) {
		final Thread serverThread = new Thread(new Pop3Server());
		serverThread.start();
	}
}