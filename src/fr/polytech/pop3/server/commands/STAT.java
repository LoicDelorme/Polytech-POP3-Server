package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.commands.results.SuccessCommandResult;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a STAT POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class STAT extends Command {

	/**
	 * The STAT message.
	 */
	private static final String STAT_MESSAGE = "%d %d";

	/**
	 * The command name.
	 */
	public static final String COMMAND_NAME = "STAT";

	/**
	 * Create a STAT POP 3 command.
	 */
	public STAT() {
		super(COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		return new SuccessCommandResult(String.format(STAT_MESSAGE, user.getNumberOfUnmarkedMessages(), user.getSizeOfUnmarkedMessages()));
	}
}