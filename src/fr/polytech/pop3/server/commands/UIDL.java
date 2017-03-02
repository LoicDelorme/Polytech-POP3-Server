package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;

/**
 * This class represents an UIDL POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class UIDL extends Command {

	/**
	 * The UIDL command name.
	 */
	public static final String UIDL_COMMAND_NAME = "UIDL";

	/**
	 * Create an UIDL POP 3 command.
	 */
	public UIDL() {
		super(UIDL_COMMAND_NAME);
	}

	@Override
	public CommandResult run(String[] parameters) {
		return null;
	}
}