package fr.polytech.pop3.server.states;

import fr.polytech.pop3.server.commands.APOP;
import fr.polytech.pop3.server.commands.QUIT;
import fr.polytech.pop3.server.commands.USER;
import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.ErrorCommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.states.results.StateResult;

/**
 * This class represents an authorization state.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class AuthorizationState extends State {

	/**
	 * The goodbye message.
	 */
	private static final String GOODBYE_MESSAGE = "dewey POP3 server signing off";

	/**
	 * The error message.
	 */
	private static final String ERROR_MESSAGE = "command not granted in this state";

	/**
	 * Create an authorization state.
	 */
	public AuthorizationState() {
		super();
	}

	@Override
	protected StateResult executeCommand(String command, String[] parameters) {
		String message = null;
		State nextState = null;

		switch (command) {
			case APOP.COMMAND_NAME:
				CommandResult apopCommandResult = COMMANDS.get(command).execute(this.user, parameters);
				message = apopCommandResult.toString();
				nextState = apopCommandResult.wasWellExecuted() ? new TransactionState(apopCommandResult.getUser()) : this;
				break;
			case USER.COMMAND_NAME:
				CommandResult userCommandResult = COMMANDS.get(command).execute(this.user, parameters);
				message = userCommandResult.toString();
				nextState = userCommandResult.wasWellExecuted() ? new IdentifiedState(userCommandResult.getUser()) : this;
				break;
			case QUIT.COMMAND_NAME:
				message = new SuccessCommandResult(GOODBYE_MESSAGE).toString();
				nextState = null;
				break;
			default:
				message = new ErrorCommandResult(ERROR_MESSAGE).toString();
				nextState = this;
				break;
		}

		return new StateResult(message, nextState);
	}
}