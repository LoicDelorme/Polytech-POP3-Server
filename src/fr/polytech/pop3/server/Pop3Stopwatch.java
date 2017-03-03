package fr.polytech.pop3.server;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents a POP 3 stopwatch.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Pop3Stopwatch extends Thread implements Pop3TimerObserver {

	/**
	 * The number of nano per second.
	 */
	private static final long NANO_PER_SECOND = 1000000000;

	/**
	 * The sleep delay.
	 */
	private static final int SLEEP_DELAY = 100;

	/**
	 * The logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(Pop3Stopwatch.class.getName());

	/**
	 * The connection timeout.
	 */
	private final int connectionTimeout;

	/**
	 * The POP 3 timer observable.
	 */
	private final Pop3TimerObservable pop3TimerObservable;

	/**
	 * The start time.
	 */
	private long startTime;

	/**
	 * If the stopwatch is running.
	 */
	private boolean isRunning;

	/**
	 * Create a POP 3 stopwatch.
	 * 
	 * @param connectionTimeout
	 *            The connection timeout.
	 * @param pop3TimerObservable
	 *            The POP 3 timer observable.
	 */
	public Pop3Stopwatch(int connectionTimeout, Pop3TimerObservable pop3TimerObservable) {
		this.connectionTimeout = connectionTimeout;
		this.pop3TimerObservable = pop3TimerObservable;
		this.isRunning = false;
	}

	@Override
	public void notifyActivity() {
		this.startTime = System.nanoTime();
	}

	@Override
	public void notifyStop() {
		this.isRunning = false;
	}

	@Override
	public void run() {
		this.startTime = System.nanoTime();
		this.isRunning = true;

		while (this.isRunning) {
			try {
				if ((System.nanoTime() - this.startTime) / NANO_PER_SECOND > this.connectionTimeout) {
					this.isRunning = false;
					this.pop3TimerObservable.notifyAutoLogout();
				}

				Thread.sleep(SLEEP_DELAY);
			} catch (InterruptedException e) {
				LOGGER.log(Level.SEVERE, "POP 3 stopwatch failed to enter into sleep mode.");
			}
		}
	}
}