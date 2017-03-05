package fr.polytech.pop3.server.users;

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
	 */
	public Message(String UUID, int index, String content) {
		this.UUID = UUID;
		this.index = index;
		this.content = content;
		this.headers = getHeaders(content);
		this.body = getBody(content);
		this.size = getSize(content);
		this.isMarked = false;
	}

	/**
	 * Get the headers from a message's content.
	 * 
	 * @param content
	 *            The message's content.
	 * @return The headers of the message.
	 */
	private String getHeaders(String content) {
		return null;
	}

	/**
	 * Get the body from a message's content.
	 * 
	 * @param content
	 *            The message's content.
	 * @return The body of the message.
	 */
	private String getBody(String content) {
		return null;
	}

	/**
	 * Get the size from a message's content.
	 * 
	 * @param content
	 *            The message's content.
	 * @return The size of the message.
	 */
	private int getSize(String content) {
		return 0;
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