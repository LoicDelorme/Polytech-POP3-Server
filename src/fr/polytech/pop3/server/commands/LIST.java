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
	 * The basic LIST message.
	 */
	private static final String BASIC_LIST_MESSAGE = "%d %d";

	/**
	 * The enhanced LIST message.
	 */
	private static final String ENHANCED_LIST_MESSAGE = "%d message(s) (%d octet(s))\r\n%s\r\n.";

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
	public static final String COMMAND_NAME = "LIST";

	/**
	 * Create a LIST POP 3 command.
	 */
	public LIST() {
		super(COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		switch (parameters.length) {
			case 0:
				return new SuccessCommandResult(String.format(ENHANCED_LIST_MESSAGE, user.getNumberOfUnmarkedMessages(), user.getSizeOfUnmarkedMessages(), user.listUnmarkedMessages().stream().map(message -> String.format(BASIC_LIST_MESSAGE, message.getIndex(), message.getSize())).collect(Collectors.joining("\r\n"))));
			case 1:
				try {
					final int numberOfMessages = user.getNumberOfUnmarkedMessages();
					final boolean isMaildropEmpty = numberOfMessages == 0;
					final Message message = user.getMessage(Integer.parseInt(parameters[0]));

					if (message == null) {
						return new ErrorCommandResult(isMaildropEmpty ? EMPTY_MAILDROP_ERROR_MESSAGE : String.format(INVALID_INDEX_ERROR_MESSAGE, numberOfMessages));
					}

					return new SuccessCommandResult(String.format(BASIC_LIST_MESSAGE, message.getIndex(), message.getSize()));
				} catch (NumberFormatException e) {
					return new ErrorCommandResult(INVALID_PARAMETER_ERROR_MESSAGE);
				}
			default:
				return new ErrorCommandResult(INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE);
		}
	}
}