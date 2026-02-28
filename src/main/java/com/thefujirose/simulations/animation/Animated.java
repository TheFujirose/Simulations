package com.thefujirose.simulations.animation;

/**
 * Represents main.java.com.thefujirose.simulations.app.animation that starts, stops, pauses, and runs.
 * 
 * @author Carson Fujita
 * @since 1.0
 */
public interface Animated extends Runnable {

	/**
	 * Starts the main.java.com.thefujirose.simulations.app.animation.
	 * @since 1.0
	 * @see #stop()
	 */
	void start();

	/**
	 * Stops the main.java.com.thefujirose.simulations.app.animation thread.
	 * @since 1.0
	 * @see #start()
	 */
	void stop();
	
	/**
	 * Pauses the main.java.com.thefujirose.simulations.app.animation thread.
	 * @since 1.0
	 * @see #resume()
	 */
	void pause();
	
	/**
	 * Resumes the main.java.com.thefujirose.simulations.app.animation from a pause.
	 * @since 1.0
	 * @see #pause()
	 */
	void resume();
}
