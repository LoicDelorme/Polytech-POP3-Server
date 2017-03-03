package fr.polytech.pop3.server;

/**
 * This interface represents a POP 3 timer observable.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public interface Pop3TimerObservable {

	/**
	 * Notify it's time to auto logout the session.
	 */
	public void notifyAutoLogout();
}