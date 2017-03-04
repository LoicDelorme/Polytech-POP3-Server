package fr.polytech.pop3.server.states.results;

import fr.polytech.pop3.server.states.State;

/**
 * This class represents a state result.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class StateResult {

	/**
	 * The message.
	 */
	private final String message;

	/**
	 * The next state.
	 */
	private final State nextState;

	/**
	 * Create a state result.
	 * 
	 * @param message
	 *            The message.
	 * @param nextState
	 *            The next state.
	 */
	public StateResult(String message, State nextState) {
		this.message = message;
		this.nextState = nextState;
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