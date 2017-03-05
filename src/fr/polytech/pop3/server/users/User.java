package fr.polytech.pop3.server.users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a user.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class User {

	/**
	 * The username.
	 */
	private final String username;

	/**
	 * The password.
	 */
	private final String password;

	/**
	 * The messages.
	 */
	private final List<Message> messages;

	/**
	 * Create a user.
	 * 
	 * @param username
	 *            The username.
	 * @param password
	 *            The password.
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.messages = new ArrayList<Message>();
	}

	/**
	 * Get the username.
	 * 
	 * @return The username.
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Get the password.
	 * 
	 * @return The password.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Get the number of unmarked messages.
	 * 
	 * @return The number of unmarked messages.
	 */
	public int getNumberOfMessages() {
		return listMessages().size();
	}

	/**
	 * Get the size of unmarked messages.
	 * 
	 * @return The size of unmarked messages.
	 */
	public int getSizeOfMessages() {
		return this.messages.stream().filter(message -> !message.isMarked()).mapToInt(message -> message.getSize()).sum();
	}

	/**
	 * List all unmarked messages.
	 * 
	 * @return The unmarked messages.
	 */
	public List<Message> listMessages() {
		return this.messages.stream().filter(message -> !message.isMarked()).collect(Collectors.toList());
	}

	/**
	 * List a message.
	 * 
	 * @param index
	 *            The index.
	 * @return NULL if there is no message at the specified index, else the requested message.
	 */
	public Message listMessage(int index) {
		final int messageIndex = index - 1;
		final List<Message> messages = listMessages();

		if (messageIndex >= 0 && messageIndex < messages.size()) {
			return messages.get(messageIndex);
		}

		return null;
	}

	/**
	 * Unmark all marked messages.
	 */
	public void unmarkMessages() {
		this.messages.stream().filter(message -> message.isMarked()).forEach(message -> message.unmark());
	}
}