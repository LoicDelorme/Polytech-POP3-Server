package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a RETR POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class RETR extends Command {

	/**
	 * The RETR command name.
	 */
	public static final String RETR_COMMAND_NAME = "RETR";

	/**
	 * Create a RETR POP 3 command.
	 */
	public RETR() {
		super(RETR_COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		return null;
	}
}