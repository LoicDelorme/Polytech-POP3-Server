package fr.polytech.pop3.server.states;

import fr.polytech.pop3.server.commands.PASS;
import fr.polytech.pop3.server.commands.QUIT;
import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.ErrorCommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.states.results.StateResult;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents an identified state.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class IdentifiedState extends State {

	/**
	 * The goodbye message.
	 */
	private static final String GOODBYE_MESSAGE = "dewey POP3 server signing off";

	/**
	 * The error message.
	 */
	private static final String ERROR_MESSAGE = "command not granted in this state";

	/**
	 * Create an identified state.
	 * 
	 * @param user
	 *            The user.
	 */
	public IdentifiedState(User user) {
		super(user);
	}

	@Override
	protected StateResult executeCommand(String command, String[] parameters) {
		String message = null;
		State nextState = null;

		switch (command) {
			case PASS.COMMAND_NAME:
				final CommandResult passCommandResult = COMMANDS.get(command).execute(this.user, parameters);
				message = passCommandResult.toString();
				nextState = passCommandResult.wasWellExecuted() ? new TransactionState(passCommandResult.getUser()) : this;
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