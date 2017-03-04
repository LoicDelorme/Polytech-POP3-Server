package fr.polytech.pop3.server.commands.results;

import fr.polytech.pop3.server.users.User;

/**
 * This class represents an error command result.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class ErrorCommandResult extends CommandResult {

	/**
	 * The error message.
	 */
	private static final String ERROR_MESSAGE = "-ERR %s";

	/**
	 * Create an error command result.
	 * 
	 * @param message
	 *            The command result message.
	 */
	public ErrorCommandResult(String message) {
		super(CommandResultStatus.ERROR, message);
	}

	/**
	 * Create an error command result.
	 * 
	 * @param message
	 *            The command result message.
	 * @param user
	 *            The command result user.
	 */
	public ErrorCommandResult(String message, User user) {
		super(CommandResultStatus.ERROR, message, user);
	}

	@Override
	public String toString() {
		return String.format(ERROR_MESSAGE, this.message);
	}
}