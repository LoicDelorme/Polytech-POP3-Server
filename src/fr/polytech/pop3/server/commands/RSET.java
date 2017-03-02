package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;

/**
 * This class represents a RSET POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class RSET extends Command {

	/**
	 * The RSET command name.
	 */
	public static final String RSET_COMMAND_NAME = "RSET";

	/**
	 * Create a RSET POP 3 command.
	 */
	public RSET() {
		super(RSET_COMMAND_NAME);
	}

	@Override
	public CommandResult run(String[] parameters) {
		return null;
	}
}