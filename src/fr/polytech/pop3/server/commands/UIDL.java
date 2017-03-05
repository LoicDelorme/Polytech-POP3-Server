package fr.polytech.pop3.server.commands;

import java.util.stream.Collectors;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.ErrorCommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.users.Message;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents an UIDL POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class UIDL extends Command {

	/**
	 * The basic UIDL message.
	 */
	private static final String BASIC_UIDL_MESSAGE = "%d %s";

	/**
	 * The enhanced UIDL message.
	 */
	private static final String ENHANCED_UIDL_MESSAGE = "UIDLs\r\n%s\r\n.";

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
	public static final String COMMAND_NAME = "UIDL";

	/**
	 * Create an UIDL POP 3 command.
	 */
	public UIDL() {
		super(COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		switch (parameters.length) {
			case 0:
				return new SuccessCommandResult(String.format(ENHANCED_UIDL_MESSAGE, user.listMessages().stream().map(message -> String.format(BASIC_UIDL_MESSAGE, message.getIndex(), message.getUUID())).collect(Collectors.joining("\r\n"))));
			case 1:
				try {
					final int index = Integer.parseInt(parameters[0]);
					final int numberOfMessages = user.getNumberOfMessages();
					final boolean isMaildropEmpty = numberOfMessages == 0;
					final Message message = isMaildropEmpty ? null : user.listMessage(index);

					if (message == null) {
						return new ErrorCommandResult(isMaildropEmpty ? EMPTY_MAILDROP_ERROR_MESSAGE : String.format(INVALID_INDEX_ERROR_MESSAGE, numberOfMessages));
					}

					return new SuccessCommandResult(String.format(BASIC_UIDL_MESSAGE, message.getIndex(), message.getUUID()));
				} catch (NumberFormatException e) {
					return new ErrorCommandResult(INVALID_PARAMETER_ERROR_MESSAGE);
				}
			default:
				return new ErrorCommandResult(INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE);
		}
	}
}