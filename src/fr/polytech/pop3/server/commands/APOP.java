package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents an APOP POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class APOP extends Command {

	/**
	 * The APOP command name.
	 */
	public static final String APOP_COMMAND_NAME = "APOP";

	/**
	 * Create an APOP POP 3 command.
	 */
	public APOP() {
		super(APOP_COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		return null;
	}
}