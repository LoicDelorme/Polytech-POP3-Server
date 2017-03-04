package fr.polytech.pop3.server.commands.results;

import fr.polytech.pop3.server.states.State;

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
	private final CommandResultStatus commandResultStatus;

	/**
	 * The message.
	 */
	private final String message;

	/**
	 * The next state.
	 */
	private final State nextState;

	/**
	 * Create a command result.
	 * 
	 * @param status
	 *            The command result status.
	 * @param message
	 *            The message.
	 * @param nextState
	 *            The next state.
	 */
	public CommandResult(CommandResultStatus status, String message, State nextState) {
		this.commandResultStatus = status;
		this.message = message;
		this.nextState = nextState;
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

	/**
	 * Get the next state.
	 * 
	 * @return The next state.
	 */
	public State getNextState() {
		return this.nextState;
	}
}