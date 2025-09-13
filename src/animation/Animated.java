package animation;

/**
 * Represents animation that starts, stops, pauses, and runs. 
 * 
 * @author Carson Fujita
 * @since 1.0
 */
public interface Animated extends Runnable {

	/**
	 * Starts the animation.
	 * @since 1.0
	 * @see #stop()
	 */
	void start();

	/**
	 * Stops the animation thread.
	 * @since 1.0
	 * @see #start()
	 */
	void stop();
	
	/**
	 * Pauses the animation thread.
	 * @since 1.0
	 * @see #resume()
	 */
	void pause();
	
	/**
	 * Resumes the animation from a pause.
	 * @since 1.0
	 * @see #pause()
	 */
	void resume();
}
