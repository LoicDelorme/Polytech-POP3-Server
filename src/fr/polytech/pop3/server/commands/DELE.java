package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a DELE POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class DELE extends Command {

	/**
	 * The DELE command name.
	 */
	public static final String DELE_COMMAND_NAME = "DELE";

	/**
	 * Create a DELE POP 3 command.
	 */
	public DELE() {
		super(DELE_COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		return null;
	}
}