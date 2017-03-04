package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a QUIT POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class QUIT extends Command {

	/**
	 * The QUIT command name.
	 */
	public static final String QUIT_COMMAND_NAME = "QUIT";

	/**
	 * Create a QUIT POP 3 command.
	 */
	public QUIT() {
		super(QUIT_COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		return null;
	}
}