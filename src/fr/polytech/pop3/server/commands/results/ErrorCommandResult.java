package fr.polytech.pop3.server.commands.results;

/**
 * This class represents an error command result.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class ErrorCommandResult extends CommandResult {

	/**
	 * Create an error command result.
	 * 
	 * @param message
	 *            The message.
	 */
	public ErrorCommandResult(String message) {
		super(CommandResultStatus.ERROR, message);
	}
}