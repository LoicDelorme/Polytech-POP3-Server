package fr.polytech.pop3.server.users;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import fr.polytech.pop3.server.users.exceptions.FailedRemoveMessageException;
import fr.polytech.pop3.server.users.exceptions.InboxAlreadyLockedException;
import fr.polytech.pop3.server.users.exceptions.InvalidPasswordException;
import fr.polytech.pop3.server.users.exceptions.InvalidUsernameException;

/**
 * This class represents a user.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class User {

	/**
	 * The inboxes path.
	 */
	private static final String INBOXES_PATH = "inboxes" + File.separator;

	/**
	 * The password file.
	 */
	private static final String PASSWORD_FILE = ".password";

	/**
	 * The lock file.
	 */
	private static final String LOCK_FILE = ".lock";

	/**
	 * The logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(User.class.getName());

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
	 * @throws InvalidUsernameException
	 *             If the user doesn't exist.
	 */
	public User(String username) throws InvalidUsernameException {
		this.username = username;
		this.password = null;
		this.messages = null;
		checkUsername();
	}

	/**
	 * Create a user.
	 * 
	 * @param username
	 *            The username.
	 * @param password
	 *            The password.
	 * @throws InvalidUsernameException
	 *             If the user doesn't exist.
	 * @throws InvalidPasswordException
	 *             If the password is not valid.
	 * @throws InboxAlreadyLockedException
	 *             If the inbox is already locked.
	 */
	public User(String username, String password) throws InvalidUsernameException, InvalidPasswordException, InboxAlreadyLockedException {
		this.username = username;
		this.password = password;
		this.messages = new ArrayList<Message>();
		checkUsername();
		checkPassword();
		lockInbox();
		loadMessages();
	}

	/**
	 * Check the username.
	 * 
	 * @throws InvalidUsernameException
	 *             If the user doesn't exist.
	 */
	private void checkUsername() throws InvalidUsernameException {
		final File userInbox = new File(INBOXES_PATH + this.username);
		if (!userInbox.exists()) {
			throw new InvalidUsernameException();
		}
	}

	/**
	 * Check the password.
	 * 
	 * @throws InvalidPasswordException
	 *             If the password is not valid.
	 */
	private void checkPassword() throws InvalidPasswordException {
		try {
			final String storedPassword = Files.readAllLines(new File(INBOXES_PATH + this.username + File.separator + PASSWORD_FILE).toPath()).get(0);
			if (!storedPassword.equals(this.password)) {
				throw new InvalidPasswordException();
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to load stored password.", e);
		}
	}

	/**
	 * Lock the user's inbox.
	 * 
	 * @throws InboxAlreadyLockedException
	 *             If the inbox is already locked.
	 */
	private void lockInbox() throws InboxAlreadyLockedException {
		try {
			final File lockFile = new File(INBOXES_PATH + this.username + File.separator + LOCK_FILE);
			if (!lockFile.createNewFile()) {
				throw new InboxAlreadyLockedException();
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to load stored password.", e);
		}
	}

	/**
	 * Load the user's messages.
	 */
	private void loadMessages() {
		final File[] messages = new File(INBOXES_PATH + this.username + File.separator).listFiles(file -> !file.isHidden());
		File message = null;
		for (int index = 0; index < messages.length; index++) {
			try {
				message = messages[index];
				this.messages.add(new Message(message.getName(), index, Files.readAllLines(message.toPath()), (int) message.length()));
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Failed to read message's content", e);
			}
		}
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

	/**
	 * Delete all marked messages.
	 * 
	 * @return The number of remaining messages.
	 * @throws FailedRemoveMessageException
	 *             If there is a problem while trying to remove marked messages.
	 */
	public int deleteMessages() throws FailedRemoveMessageException {
		boolean allDeleted = true;
		final List<Message> markedMessages = this.messages.stream().filter(messages -> messages.isMarked()).collect(Collectors.toList());
		for (Message markedMessage : markedMessages) {
			allDeleted &= new File(INBOXES_PATH + this.username + File.separator + markedMessage.getUUID()).delete();
			this.messages.remove(markedMessage);
		}

		unlockInbox();

		if (!allDeleted) {
			throw new FailedRemoveMessageException();
		}

		return this.messages.size();
	}

	/**
	 * Unlock the user's inbox.
	 */
	private void unlockInbox() {
		final File lockFile = new File(INBOXES_PATH + this.username + File.separator + LOCK_FILE);
		lockFile.delete();
	}
}