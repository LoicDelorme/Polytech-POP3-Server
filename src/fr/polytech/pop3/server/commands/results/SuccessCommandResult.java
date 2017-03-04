package fr.polytech.pop3.server.commands.results;

/**
 * This class represents a success command result.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class SuccessCommandResult extends CommandResult {

	/**
	 * Create a success command result.
	 * 
	 * @param message
	 *            The message.
	 */
	public SuccessCommandResult(String message) {
		super(CommandResultStatus.SUCCESS, message);
	}
}