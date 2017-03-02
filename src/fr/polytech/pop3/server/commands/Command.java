package fr.polytech.pop3.server.commands;

import fr.polytech.pop3.server.commands.results.CommandResult;

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
	 * Run the POP 3 command.
	 * 
	 * @param parameters
	 *            The parameters (optionnal).
	 * @return The command result.
	 */
	public abstract CommandResult run(String[] parameters);

	@Override
	public String toString() {
		return "Command [name=" + this.name + "]";
	}
}