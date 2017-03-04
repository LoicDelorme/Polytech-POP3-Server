package fr.polytech.pop3.server.commands.results;

import fr.polytech.pop3.server.states.State;

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
	 * @param nextState
	 *            The next state.
	 */
	public SuccessCommandResult(String message, State nextState) {
		super(CommandResultStatus.SUCCESS, message, nextState);
	}
}