package fr.polytech.pop3.server.states;

import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.states.results.StateResult;

/**
 * This class represents a closed state.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class ClosedState extends State {

	/**
	 * The welcome message.
	 */
	private static final String WELCOME_MESSAGE = "POP3 server ready";

	/**
	 * Create a closed state.
	 */
	public ClosedState() {
		super();
	}

	@Override
	protected StateResult executeCommand(String command, String[] parameters) {
		return new StateResult(new SuccessCommandResult(WELCOME_MESSAGE).toString(), new AuthorizationState());
	}
}