package fr.polytech.pop3.server.commands.results;

import fr.polytech.pop3.server.states.State;

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
	 * @param nextState
	 *            The next state.
	 */
	public ErrorCommandResult(String message, State nextState) {
		super(CommandResultStatus.ERROR, message, nextState);
	}
}