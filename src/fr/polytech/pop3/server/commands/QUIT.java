package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.ErrorCommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.users.User;
import fr.polytech.pop3.server.users.exceptions.FailedRemoveMessageException;

/**
 * This class represents a QUIT POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class QUIT extends Command {

	/**
	 * The empty inbox message.
	 */
	private static final String EMPTY_INBOX_MESSAGE = "dewey POP3 server signing off (maildrop empty)";

	/**
	 * The QUIT message.
	 */
	private static final String QUIT_MESSAGE = "dewey POP3 server signing off (%d message(s) left)";

	/**
	 * The deleted messages not removed error message.
	 */
	private static final String DELETED_MESSAGES_NOT_REMOVED_ERROR_MESSAGE = "some deleted messages not removed";

	/**
	 * The command name.
	 */
	public static final String COMMAND_NAME = "QUIT";

	/**
	 * Create a QUIT POP 3 command.
	 */
	public QUIT() {
		super(COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		try {
			final int numberOfRemainingMessages = user.deleteAllMarkedMessages();
			return new SuccessCommandResult(numberOfRemainingMessages == 0 ? EMPTY_INBOX_MESSAGE : String.format(QUIT_MESSAGE, numberOfRemainingMessages));
		} catch (FailedRemoveMessageException e) {
			return new ErrorCommandResult(DELETED_MESSAGES_NOT_REMOVED_ERROR_MESSAGE);
		}
	}
}