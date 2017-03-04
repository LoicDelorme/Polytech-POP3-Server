package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a NOOP POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class NOOP extends Command {

	/**
	 * The NOOP command name.
	 */
	public static final String NOOP_COMMAND_NAME = "NOOP";

	/**
	 * Create a NOOP POP 3 command.
	 */
	public NOOP() {
		super(NOOP_COMMAND_NAME);
	}

	@Override
	public CommandResult execute(User user, String[] parameters) {
		return null;
	}
}