package fr.polytech.pop3.server.commands;

import java.util.Arrays;
import java.util.stream.Collectors;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.ErrorCommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.users.Message;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a TOP POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class TOP extends Command {

	/**
	 * The TOP message.
	 */
	private static final String TOP_MESSAGE = "\r\n%s\r\n\r\n%s\r\n.";

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
	 * The command name.
	 */
	public static final String COMMAND_NAME = "TOP";

	/**
	 * Create a TOP POP 3 command.
	 */
	public TOP() {
		super(COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		if (parameters.length != 2) {
			return new ErrorCommandResult(INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE);
		}

		try {
			final int numberOfLines = Integer.parseInt(parameters[1]);
			final int numberOfMessages = user.getNumberOfUnmarkedMessages();
			final boolean isMaildropEmpty = numberOfMessages == 0;
			final Message message = user.getMessage(Integer.parseInt(parameters[0]));

			if (message == null) {
				return new ErrorCommandResult(isMaildropEmpty ? EMPTY_MAILDROP_ERROR_MESSAGE : String.format(INVALID_INDEX_ERROR_MESSAGE, numberOfMessages));
			}

			return new SuccessCommandResult(String.format(TOP_MESSAGE, message.getHeaders(), Arrays.asList(message.getBody().split("\n")).stream().limit(numberOfLines).collect(Collectors.joining("\n"))));
		} catch (NumberFormatException e) {
			return new ErrorCommandResult(INVALID_PARAMETER_ERROR_MESSAGE);
		}
	}
}