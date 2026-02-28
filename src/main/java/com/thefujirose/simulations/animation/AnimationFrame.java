/**
 * 
 */
package com.thefujirose.simulations.animation;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 * Defines a class that frames an main.java.com.thefujirose.simulations.app.animation for viewing.
 * 
 * @since 1.0
 */
public class AnimationFrame extends JFrame {

	private static final long serialVersionUID = -3611290876909079204L;

	/**
	 * The main.java.com.thefujirose.simulations.app.animation panel that will be rendered onto this
	 * {@link java.swing.JFrame}.
	 * 
	 * @since 1.0
	 */
	private final Animation animation;

	/**
	 * The thread that runs the main.java.com.thefujirose.simulations.app.animation.
	 * 
	 * @see #animation
	 * @since 1.0
	 */
	private Thread animationThread;

	/**
	 * Creates a standard main.java.com.thefujirose.simulations.app.animation frame with a {@code width} of {@code 400} and
	 * height of {@code 400}. Uses a specified name and main.java.com.thefujirose.simulations.app.animation.
	 * 
	 * @param name      - name of frame
	 * @param animation - the main.java.com.thefujirose.simulations.app.animation displayed on frame
	 * @throws HeadlessException
	 * @since 1.0
	 */
	public AnimationFrame(String name, Animation animation) {
		super(name);
		this.animation = animation;
	}

	/**
	 * Begins main.java.com.thefujirose.simulations.app.animation thread.
	 * @since 1.0
	 */
	public void start() {
		add(this.animation);

		animationThread = new Thread(animation);
		animationThread.start();
	}

	/**
	 * Begins the main.java.com.thefujirose.simulations.app.animation thread with specified layout.
	 * @param layout - the specified layout
	 * @since 1.0
	 */
	public void start(String layout) {
		add(this.animation, layout);

		animationThread = new Thread(animation);
		animationThread.start();
	}

	/**
	 * Stops the main.java.com.thefujirose.simulations.app.animation.
	 * 
	 * @since 1.0
	 */
	public void stop() {
		animation.stop();
	}
}
