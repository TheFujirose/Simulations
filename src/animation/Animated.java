package animation;

import java.awt.Graphics2D;

/**
 * Controls animations.
 */
public interface Animated extends Runnable {

	/**
	 * Starts the animation thread
	 */
	void start();

	/**
	 * Stops the animation thread
	 */
	void stop();

	/**
	 * Prints the animation
	 * @param g - the graphics to print the animation on
	 */
	void paint(Graphics2D g);

	void setFrame(AnimationFrame animationFrame);
}
