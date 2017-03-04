package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a POP 3 command.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public abstract class Command {

	/**
	 * The command name.
	 */
	protected final String name;

	/**
	 * Create a POP 3 command.
	 * 
	 * @param name
	 *            The command name.
	 */
	public Command(String name) {
		this.name = name;
	}

	/**
	 * Get the command name.
	 * 
	 * @return The command name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Execute the POP 3 command.
	 * 
	 * @param user
	 *            The user (optionnal).
	 * @param parameters
	 *            The parameters (optionnal).
	 * @return The command result.
	 */
	public abstract CommandResult execute(User user, String[] parameters);

	@Override
	public String toString() {
		return "Command [name=" + this.name + "]";
	}
}