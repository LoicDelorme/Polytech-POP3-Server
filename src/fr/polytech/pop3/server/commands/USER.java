package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.ErrorCommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.users.User;
import fr.polytech.pop3.server.users.exceptions.InvalidUsernameException;

/**
 * This class represents a USER POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class USER extends Command {

	/**
	 * The USER message.
	 */
	private static final String USER_MESSAGE = "%s is a real hoopy frood";

	/**
	 * The invalid user error message.
	 */
	private static final String INVALID_USER_ERROR_MESSAGE = "sorry, no mailbox for %s here";

	/**
	 * The invalid number of parameters error message.
	 */
	private static final String INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE = "invalid number of parameters";

	/**
	 * The command name.
	 */
	public static final String COMMAND_NAME = "USER";

	/**
	 * Create a USER POP 3 command.
	 */
	public USER() {
		super(COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		if (parameters.length != 1) {
			return new ErrorCommandResult(INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE);
		}

		final String username = parameters[0];
		try {
			return new SuccessCommandResult(String.format(USER_MESSAGE, username), new User(username, user.getSecurityMessage()));
		} catch (InvalidUsernameException e) {
			return new ErrorCommandResult(String.format(INVALID_USER_ERROR_MESSAGE, username));
		}
	}
}