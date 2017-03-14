package fr.polytech.pop3.server.states;

import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;

import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.states.results.StateResult;
import fr.polytech.pop3.server.users.User;

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
	private static final String WELCOME_MESSAGE = "POP3 server ready %s";

	/**
	 * Create a closed state.
	 */
	public ClosedState() {
		super();
	}

	@Override
	protected StateResult executeCommand(String command, String[] parameters) {
		final String securityMessage = String.format("<%s@%s>", ManagementFactory.getRuntimeMXBean().getName(), LocalDateTime.now().toString());
		return new StateResult(new SuccessCommandResult(String.format(WELCOME_MESSAGE, securityMessage)).toString(), new AuthorizationState(new User(securityMessage)));
	}
}