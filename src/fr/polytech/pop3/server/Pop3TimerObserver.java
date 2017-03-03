package fr.polytech.pop3.server;

/**
 * This interface represents a POP 3 timer observer.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public interface Pop3TimerObserver {

	/**
	 * Notify an activity has been detected.
	 */
	public void notifyActivity();

	/**
	 * Notify the timer to be stopped.
	 */
	public void notifyStop();
}