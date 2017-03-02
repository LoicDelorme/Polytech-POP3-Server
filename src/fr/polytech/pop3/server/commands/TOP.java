package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;

/**
 * This class represents a TOP POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class TOP extends Command {

	/**
	 * The TOP command name.
	 */
	public static final String TOP_COMMAND_NAME = "TOP";

	/**
	 * Create a TOP POP 3 command.
	 */
	public TOP() {
		super(TOP_COMMAND_NAME);
	}

	@Override
	public CommandResult run(String[] parameters) {
		return null;
	}
}