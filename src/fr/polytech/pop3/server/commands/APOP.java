package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.ErrorCommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.users.User;
import fr.polytech.pop3.server.users.exceptions.InboxAlreadyLockedException;
import fr.polytech.pop3.server.users.exceptions.InvalidPasswordException;
import fr.polytech.pop3.server.users.exceptions.InvalidUsernameException;

/**
 * This class represents an APOP POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class APOP extends Command {

	/**
	 * The APOP message.
	 */
	private static final String APOP_MESSAGE = "%s’s maildrop has %d message(s) (%d octet(s))";

	/**
	 * The invalid username message.
	 */
	private static final String INVALID_USERNAME_ERROR_MESSAGE = "sorry, no mailbox for %s here";

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
	 * The command name.
	 */
	public static final String COMMAND_NAME = "APOP";

	/**
	 * Create an APOP POP 3 command.
	 */
	public APOP() {
		super(COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		if (parameters.length != 2) {
			return new ErrorCommandResult(INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE);
		}

		final String username = parameters[0];
		final String password = parameters[1];
		try {
			final User user_ = new User(username, password);
			return new SuccessCommandResult(String.format(APOP_MESSAGE, username, user_.getNumberOfUnmarkedMessages(), user_.getSizeOfUnmarkedMessages()), user_);
		} catch (InvalidUsernameException e) {
			return new ErrorCommandResult(String.format(INVALID_USERNAME_ERROR_MESSAGE, username));
		} catch (InvalidPasswordException e) {
			return new ErrorCommandResult(INVALID_PASSWORD_ERROR_MESSAGE);
		} catch (InboxAlreadyLockedException e) {
			return new ErrorCommandResult(INBOX_ALREADY_LOCKED_ERROR_MESSAGE);
		}
	}
}