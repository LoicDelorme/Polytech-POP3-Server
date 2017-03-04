package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a PASS POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class PASS extends Command {

	/**
	 * The PASS command name.
	 */
	public static final String PASS_COMMAND_NAME = "PASS";

	/**
	 * Create a PASS POP 3 command.
	 */
	public PASS() {
		super(PASS_COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		return null;
	}
}