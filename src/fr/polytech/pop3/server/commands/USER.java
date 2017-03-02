package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;

/**
 * This class represents a USER POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class USER extends Command {

	/**
	 * The USER command name.
	 */
	public static final String USER_COMMAND_NAME = "USER";

	/**
	 * Create a USER POP 3 command.
	 */
	public USER() {
		super(USER_COMMAND_NAME);
	}

	@Override
	public CommandResult run(String[] parameters) {
		return null;
	}
}