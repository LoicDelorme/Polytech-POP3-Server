package fr.polytech.pop3.server.commands.results;

import fr.polytech.pop3.server.users.User;

/**
 * This class represents a command result.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public abstract class CommandResult {

	/**
	 * The command result status.
	 */
	private final CommandResultStatus status;

	/**
	 * The command result message.
	 */
	protected final String message;

	/**
	 * The command result user.
	 */
	protected final User user;

	/**
	 * Create a command result.
	 * 
	 * @param status
	 *            The command result status.
	 * @param message
	 *            The command result message.
	 */
	public CommandResult(CommandResultStatus status, String message) {
		this(status, message, null);
	}

	/**
	 * Create a command result.
	 * 
	 * @param status
	 *            The command result status.
	 * @param message
	 *            The command result message.
	 * @param user
	 *            The command result user.
	 */
	public CommandResult(CommandResultStatus status, String message, User user) {
		this.status = status;
		this.message = message;
		this.user = user;
	}

	/**
	 * Check if the command was well executed.
	 * 
	 * @return True if it was well executed, else False.
	 */
	public boolean wasWellExecuted() {
		return CommandResultStatus.SUCCESS == this.status;
	}

	/**
	 * Get the user.
	 * 
	 * @return The user.
	 */
	public User getUser() {
		return this.user;
	}

	@Override
	public abstract String toString();
}