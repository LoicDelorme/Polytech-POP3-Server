package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;

/**
 * This class represents a LIST POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class LIST extends Command {

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
	public CommandResult run(String[] parameters) {
		return null;
	}
}