package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.ErrorCommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.users.User;
import fr.polytech.pop3.server.users.exceptions.InboxAlreadyLockedException;
import fr.polytech.pop3.server.users.exceptions.InvalidPasswordException;
import fr.polytech.pop3.server.users.exceptions.InvalidUsernameException;

/**
 * This class represents a PASS POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class PASS extends Command {

	/**
	 * The PASS message.
	 */
	private static final String PASS_MESSAGE = "%s’s maildrop has %d message(s) (%d octet(s))";

	/**
	 * The invalid password message.
	 */
	private static final String INVALID_PASSWORD_ERROR_MESSAGE = "invalid password";

	/**
	 * The inbox already locked message.
	 */
	private static final String INBOX_ALREADY_LOCKED_ERROR_MESSAGE = "maildrop already locked";

	/**
	 * The invalid number of parameters error message.
	 */
	private static final String INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE = "invalid number of parameters";

	/**
	 * The PASS command name.
	 */
	public static final String COMMAND_NAME = "PASS";

	/**
	 * Create a PASS POP 3 command.
	 */
	public PASS() {
		super(COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		if (parameters.length != 1) {
			return new ErrorCommandResult(INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE);
		}

		try {
			final User connectedUser = new User(user.getUsername(), parameters[0]);
			return new SuccessCommandResult(String.format(PASS_MESSAGE, connectedUser.getUsername(), connectedUser.getNumberOfMessages(), connectedUser.getSizeOfMessages()), connectedUser);
		} catch (InvalidUsernameException e) {
			return new ErrorCommandResult(INVALID_PASSWORD_ERROR_MESSAGE); // Cannot occur in this case but for compilation purpose.
		} catch (InvalidPasswordException e) {
			return new ErrorCommandResult(INVALID_PASSWORD_ERROR_MESSAGE);
		} catch (InboxAlreadyLockedException e) {
			return new ErrorCommandResult(INBOX_ALREADY_LOCKED_ERROR_MESSAGE);
		}
	}
}