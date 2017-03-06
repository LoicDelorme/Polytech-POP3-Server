package fr.polytech.pop3.server.users;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a message.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Message {

	/**
	 * The UUID of the message.
	 */
	private final String UUID;

	/**
	 * The index of the message.
	 */
	private final int index;

	/**
	 * The content of the message (including both headers and body).
	 */
	private final String content;

	/**
	 * The headers of the message.
	 */
	private final String headers;

	/**
	 * The body of the message.
	 */
	private final String body;

	/**
	 * The size of the message.
	 */
	private final int size;

	/**
	 * If the message is marked.
	 */
	private boolean isMarked;

	/**
	 * Create a message.
	 * 
	 * @param UUID
	 *            The UUID of the message.
	 * @param index
	 *            The index of the message.
	 * @param content
	 *            The content of the message.
	 * @param size
	 *            The size of the message.
	 */
	public Message(String UUID, int index, List<String> content, int size) {
		this.UUID = UUID;
		this.index = index;
		this.size = size;
		this.isMarked = false;

		final int contentDeliminiterIndex = getContentDeliminiterIndex(content);
		this.content = content.stream().collect(Collectors.joining("\n"));
		this.headers = content.subList(0, contentDeliminiterIndex).stream().collect(Collectors.joining("\n"));
		this.body = content.subList(contentDeliminiterIndex + 1, content.size()).stream().collect(Collectors.joining("\n"));
	}

	/**
	 * Get the content delimiter index.
	 * 
	 * @param content
	 *            The content.
	 * @return The content delimiter index.
	 */
	private int getContentDeliminiterIndex(List<String> content) {
		int delimiterIndex = 0;
		while (!"".equals(content.get(delimiterIndex))) {
			delimiterIndex++;
		}

		return delimiterIndex;
	}

	/**
	 * Get the UUID of the message.
	 * 
	 * @return The UUID of the message.
	 */
	public String getUUID() {
		return this.UUID;
	}

	/**
	 * Get the index of the message.
	 * 
	 * @return The index of the message.
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * Get the content of the message.
	 * 
	 * @return The content of the message.
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * Get the headers of the message.
	 * 
	 * @return The headers of the message.
	 */
	public String getHeaders() {
		return this.headers;
	}

	/**
	 * Get the body of the message.
	 * 
	 * @return The body of the message.
	 */
	public String getBody() {
		return this.body;
	}

	/**
	 * Get the size of the message.
	 * 
	 * @return The size of the message.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Check if the message is marked.
	 * 
	 * @return True if the message is marked, else False.
	 */
	public boolean isMarked() {
		return this.isMarked;
	}

	/**
	 * Mark the message.
	 */
	public void mark() {
		this.isMarked = true;
	}

	/**
	 * Unmark the message.
	 */
	public void unmark() {
		this.isMarked = false;
	}
}