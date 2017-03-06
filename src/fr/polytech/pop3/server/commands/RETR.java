package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.ErrorCommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.users.Message;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a RETR POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class RETR extends Command {

	/**
	 * The RETR message.
	 */
	private static final String RETR_MESSAGE = "%d octet(s)\r\n%s\r\n.";

	/**
	 * The invalid number of parameters error message.
	 */
	private static final String INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE = "invalid number of parameters";

	/**
	 * The invalid parameters error message.
	 */
	private static final String INVALID_PARAMETER_ERROR_MESSAGE = "invalid parameter";

	/**
	 * The invalid message number error message.
	 */
	private static final String INVALID_MESSAGE_NUMBER_ERROR_MESSAGE = "invalid message number";

	/**
	 * The command name.
	 */
	public static final String COMMAND_NAME = "RETR";

	/**
	 * Create a RETR POP 3 command.
	 */
	public RETR() {
		super(COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		if (parameters.length != 1) {
			return new ErrorCommandResult(INVALID_NUMBER_OF_PARAMETERS_ERROR_MESSAGE);
		}

		try {
			final Message message = user.getMessage(Integer.parseInt(parameters[0]));
			if (message == null) {
				return new ErrorCommandResult(INVALID_MESSAGE_NUMBER_ERROR_MESSAGE);
			}

			return new SuccessCommandResult(String.format(RETR_MESSAGE, message.getSize(), message.getContent()));
		} catch (NumberFormatException e) {
			return new ErrorCommandResult(INVALID_PARAMETER_ERROR_MESSAGE);
		}
	}
}