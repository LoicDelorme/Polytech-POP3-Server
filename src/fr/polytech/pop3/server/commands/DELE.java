package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.ErrorCommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.users.Message;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a DELE POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class DELE extends Command {

	/**
	 * The DELE message.
	 */
	private static final String DELE_MESSAGE = "message %d deleted";

	/**
	 * The invalid number of parameters error message.
	 */
	private static final String INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE = "invalid number of parameters";

	/**
	 * The invalid parameters error message.
	 */
	private static final String INVALID_PARAMETER_ERROR_MESSAGE = "invalid parameter";

	/**
	 * The empty maildrop error message.
	 */
	private static final String EMPTY_MAILDROP_ERROR_MESSAGE = "no such message, maildrop is empty";

	/**
	 * The invalid index error message.
	 */
	private static final String INVALID_INDEX_ERROR_MESSAGE = "no such message, only %d message(s) in maildrop";

	/**
	 * The already deleted error message.
	 */
	private static final String ALREADY_DELETED_ERROR_MESSAGE = "message %d already deleted";

	/**
	 * The command name.
	 */
	public static final String COMMAND_NAME = "DELE";

	/**
	 * Create a DELE POP 3 command.
	 */
	public DELE() {
		super(COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		if (parameters.length != 1) {
			return new ErrorCommandResult(INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE);
		}

		try {
			final int index = Integer.parseInt(parameters[0]);
			final int numberOfMessages = user.getNumberOfUnmarkedMessages();
			final boolean isMaildropEmpty = numberOfMessages == 0;
			final Message message = user.getMessage(index);

			if (message == null) {
				return new ErrorCommandResult(isMaildropEmpty ? EMPTY_MAILDROP_ERROR_MESSAGE : String.format(INVALID_INDEX_ERROR_MESSAGE, numberOfMessages));
			}

			if (message.isMarked()) {
				return new ErrorCommandResult(String.format(ALREADY_DELETED_ERROR_MESSAGE, index));
			}

			message.mark();
			return new SuccessCommandResult(String.format(DELE_MESSAGE, index));
		} catch (NumberFormatException e) {
			return new ErrorCommandResult(INVALID_PARAMETER_ERROR_MESSAGE);
		}
	}
}