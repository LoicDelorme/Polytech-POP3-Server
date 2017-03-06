package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a RSET POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class RSET extends Command {

	/**
	 * The RSET message.
	 */
	private static final String RSET_MESSAGE = "maildrop has %d message(s) (%d octet(s))";

	/**
	 * The command name.
	 */
	public static final String COMMAND_NAME = "RSET";

	/**
	 * Create a RSET POP 3 command.
	 */
	public RSET() {
		super(COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		user.unmarkAllMarkedMessages();
		return new SuccessCommandResult(String.format(RSET_MESSAGE, user.getNumberOfUnmarkedMessages(), user.getSizeOfUnmarkedMessages()));
	}
}