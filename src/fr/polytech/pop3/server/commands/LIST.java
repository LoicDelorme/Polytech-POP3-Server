package fr.polytech.pop3.server.commands;

import java.util.stream.Collectors;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.ErrorCommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.users.Message;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a LIST POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class LIST extends Command {

	/**
	 * The list simple message.
	 */
	private static final String LIST_SIMPLE_MESSAGE = "%d %d";

	/**
	 * The list multiple message.
	 */
	private static final String LIST_MULTIPLE_MESSAGE = "%d message(s) (%d octet(s))";

	/**
	 * The empty messages error message.
	 */
	private static final String EMPTY_MESSAGES_ERROR_MESSAGE = "no such message, maildrop is empty";

	/**
	 * The no such message error message.
	 */
	private static final String NO_SUCH_MESSAGE_ERROR_MESSAGE = "no such message, only %d message(s) in maildrop";

	/**
	 * The LIST command name.
	 */
	public static final String LIST_COMMAND_NAME = "LIST";

	/**
	 * Create a LIST POP 3 command.
	 */
	public LIST() {
		super(LIST_COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		switch (parameters.length) {
			case 0:
				final StringBuilder messageBuilder = new StringBuilder();
				messageBuilder.append(String.format(LIST_MULTIPLE_MESSAGE, user.getNumberOfMessages(), user.getSizeOfMessages()));
				messageBuilder.append("\r\n");
				messageBuilder.append(user.listMessages().stream().map(message -> String.format(LIST_SIMPLE_MESSAGE, message.getIndex(), message.getSize())).collect(Collectors.joining("\r\n")));
				messageBuilder.append("\r\n.");

				return new SuccessCommandResult(messageBuilder.toString());
			case 1:
				try {
					final int index = Integer.parseInt(parameters[0]);
					final int numberOfMessages = user.getNumberOfMessages();
					final Message message = numberOfMessages == 0 ? null : user.listMessage(index);
					if (message == null) {
						return new ErrorCommandResult(numberOfMessages == 0 ? EMPTY_MESSAGES_ERROR_MESSAGE : String.format(NO_SUCH_MESSAGE_ERROR_MESSAGE, numberOfMessages));
					}

					return new SuccessCommandResult(String.format(LIST_SIMPLE_MESSAGE, index, message.getSize()));
				} catch (NumberFormatException e) {
					return new ErrorCommandResult(INVALID_PARAMETER_ERROR_MESSAGE);
				}
			default:
				return new ErrorCommandResult(INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE);
		}
	}
}