package fr.polytech.pop3.server.commands.results;

/**
 * This class represents a command result.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class CommandResult {

	/**
	 * The command result status.
	 */
	protected final CommandResultStatus commandResultStatus;

	/**
	 * The command result message.
	 */
	protected final String message;

	/**
	 * Create a command result.
	 * 
	 * @param status
	 *            The command result status.
	 * @param message
	 *            The command result message.
	 */
	public CommandResult(CommandResultStatus status, String message) {
		this.commandResultStatus = status;
		this.message = message;
	}

	/**
	 * Get the command result status.
	 * 
	 * @return The command result status.
	 */
	public CommandResultStatus getCommandResultStatus() {
		return this.commandResultStatus;
	}

	/**
	 * Get the message.
	 * 
	 * @return The message.
	 */
	public String getMessage() {
		return this.message;
	}
}