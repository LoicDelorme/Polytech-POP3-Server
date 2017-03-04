package fr.polytech.pop3.server.users;

/**
 * This class represents a message.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Message {

	/**
	 * The UUID.
	 */
	private final String UUID;

	/**
	 * The index.
	 */
	private final int index;

	/**
	 * The size.
	 */
	private final int size;

	/**
	 * The content.
	 */
	private final String content;

	/**
	 * If it's marked.
	 */
	private boolean isMarked;

	/**
	 * Create a message.
	 * 
	 * @param UUID
	 *            The UUID.
	 * @param index
	 *            The index.
	 * @param size
	 *            The size.
	 * @param content
	 *            The content.
	 */
	public Message(String UUID, int index, int size, String content) {
		this.UUID = UUID;
		this.index = index;
		this.size = size;
		this.content = content;
		this.isMarked = false;
	}

	/**
	 * Get the UUID.
	 * 
	 * @return The UUID.
	 */
	public String getUUID() {
		return this.UUID;
	}

	/**
	 * Get the index.
	 * 
	 * @return The index.
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * Get the size.
	 * 
	 * @return The size.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Get the content.
	 * 
	 * @return The content.
	 */
	public String getContent() {
		return this.content;
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
}