package fr.polytech.pop3.server.states;

import fr.polytech.pop3.server.commands.DELE;
import fr.polytech.pop3.server.commands.LIST;
import fr.polytech.pop3.server.commands.NOOP;
import fr.polytech.pop3.server.commands.QUIT;
import fr.polytech.pop3.server.commands.RETR;
import fr.polytech.pop3.server.commands.RSET;
import fr.polytech.pop3.server.commands.STAT;
import fr.polytech.pop3.server.commands.TOP;
import fr.polytech.pop3.server.commands.UIDL;
import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.ErrorCommandResult;
import fr.polytech.pop3.server.states.results.StateResult;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a transaction state.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class TransactionState extends State {

	/**
	 * The error message.
	 */
	private static final String ERROR_MESSAGE = "command not granted in this state";

	/**
	 * Create a transaction state.
	 * 
	 * @param user
	 *            The user.
	 */
	public TransactionState(User user) {
		super(user);
	}

	@Override
	protected StateResult executeCommand(String command, String[] parameters) {
		String message = null;
		State nextState = null;

		switch (command) {
			case DELE.COMMAND_NAME:
				CommandResult deleCommandResult = COMMANDS.get(command).execute(this.user, parameters);
				message = deleCommandResult.toString();
				nextState = this;
				break;
			case LIST.COMMAND_NAME:
				CommandResult listCommandResult = COMMANDS.get(command).execute(this.user, parameters);
				message = listCommandResult.toString();
				nextState = this;
				break;
			case NOOP.COMMAND_NAME:
				CommandResult noopCommandResult = COMMANDS.get(command).execute(this.user, parameters);
				message = noopCommandResult.toString();
				nextState = this;
				break;
			case QUIT.COMMAND_NAME:
				CommandResult quitCommandResult = COMMANDS.get(command).execute(this.user, parameters);
				message = quitCommandResult.toString();
				nextState = null;
				break;
			case RETR.COMMAND_NAME:
				CommandResult retrCommandResult = COMMANDS.get(command).execute(this.user, parameters);
				message = retrCommandResult.toString();
				nextState = this;
				break;
			case RSET.COMMAND_NAME:
				CommandResult rsetCommandResult = COMMANDS.get(command).execute(this.user, parameters);
				message = rsetCommandResult.toString();
				nextState = this;
				break;
			case STAT.COMMAND_NAME:
				CommandResult statCommandResult = COMMANDS.get(command).execute(this.user, parameters);
				message = statCommandResult.toString();
				nextState = this;
				break;
			case TOP.COMMAND_NAME:
				CommandResult topCommandResult = COMMANDS.get(command).execute(this.user, parameters);
				message = topCommandResult.toString();
				nextState = this;
				break;
			case UIDL.COMMAND_NAME:
				CommandResult uidlCommandResult = COMMANDS.get(command).execute(this.user, parameters);
				message = uidlCommandResult.toString();
				nextState = this;
				break;
			default:
				message = new ErrorCommandResult(ERROR_MESSAGE).toString();
				nextState = this;
				break;
		}

		return new StateResult(message, nextState);
	}
}