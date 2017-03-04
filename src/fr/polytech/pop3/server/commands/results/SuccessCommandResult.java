package fr.polytech.pop3.server.commands.results;

import fr.polytech.pop3.server.users.User;

/**
 * This class represents a success command result.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class SuccessCommandResult extends CommandResult {

	/**
	 * The success message.
	 */
	private static final String SUCCESS_MESSAGE = "+OK %s";

	/**
	 * Create a success command result.
	 * 
	 * @param message
	 *            The command result message.
	 */
	public SuccessCommandResult(String message) {
		super(CommandResultStatus.SUCCESS, message);
	}

	/**
	 * Create a success command result.
	 * 
	 * @param message
	 *            The command result message.
	 * @param user
	 *            The command result user.
	 */
	public SuccessCommandResult(String message, User user) {
		super(CommandResultStatus.SUCCESS, message, user);
	}

	@Override
	public String toString() {
		return String.format(SUCCESS_MESSAGE, this.message);
	}
}