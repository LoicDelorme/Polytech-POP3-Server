package fr.polytech.pop3.server.states;

import java.util.HashMap;
import java.util.Map;

import fr.polytech.pop3.server.commands.APOP;
import fr.polytech.pop3.server.commands.Command;
import fr.polytech.pop3.server.commands.DELE;
import fr.polytech.pop3.server.commands.LIST;
import fr.polytech.pop3.server.commands.NOOP;
import fr.polytech.pop3.server.commands.PASS;
import fr.polytech.pop3.server.commands.QUIT;
import fr.polytech.pop3.server.commands.RETR;
import fr.polytech.pop3.server.commands.RSET;
import fr.polytech.pop3.server.commands.STAT;
import fr.polytech.pop3.server.commands.TOP;
import fr.polytech.pop3.server.commands.UIDL;
import fr.polytech.pop3.server.commands.USER;
import fr.polytech.pop3.server.states.results.StateResult;
import fr.polytech.pop3.server.users.User;

/**
 * This class represents a state.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public abstract class State {

	/**
	 * The commands.
	 */
	protected static final Map<String, Command> COMMANDS = new HashMap<String, Command>();

	{
		COMMANDS.put(APOP.COMMAND_NAME, new APOP());
		COMMANDS.put(DELE.COMMAND_NAME, new DELE());
		COMMANDS.put(LIST.COMMAND_NAME, new LIST());
		COMMANDS.put(NOOP.COMMAND_NAME, new NOOP());
		COMMANDS.put(PASS.COMMAND_NAME, new PASS());
		COMMANDS.put(QUIT.COMMAND_NAME, new QUIT());
		COMMANDS.put(RETR.COMMAND_NAME, new RETR());
		COMMANDS.put(RSET.COMMAND_NAME, new RSET());
		COMMANDS.put(STAT.COMMAND_NAME, new STAT());
		COMMANDS.put(TOP.COMMAND_NAME, new TOP());
		COMMANDS.put(UIDL.COMMAND_NAME, new UIDL());
		COMMANDS.put(USER.COMMAND_NAME, new USER());
	}

	/**
	 * The user.
	 */
	protected final User user;

	/**
	 * Create a state.
	 */
	public State() {
		this(null);
	}

	/**
	 * Create a state.
	 * 
	 * @param user
	 *            The user.
	 */
	public State(User user) {
		this.user = user;
	}

	/**
	 * Run the command.
	 * 
	 * @param receivedCommand
	 *            The received command with its potential parameters.
	 * @return The state result.
	 */
	public StateResult runCommand(String receivedCommand) {
		String[] data = receivedCommand.split(" ");

		String command = data[0].toUpperCase();
		String[] parameters = null;

		if (data.length > 1) {
			parameters = new String[data.length - 1];
			System.arraycopy(data, 1, parameters, 0, parameters.length);
		}

		return executeCommand(command, parameters);
	}

	/**
	 * Execute the command with its parameters.
	 * 
	 * @param command
	 *            The command.
	 * @param parameters
	 *            The parameters (optionnal).
	 * @return The state result.
	 */
	protected abstract StateResult executeCommand(String command, String[] parameters);
}