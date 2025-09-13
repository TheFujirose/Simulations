/**
 * 
 */
package animation;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 * Defines a class that frames an animation for viewing.
 * 
 * @since 1.0
 */
public class AnimationFrame extends JFrame {

	private static final long serialVersionUID = -3611290876909079204L;

	/**
	 * The animation panel that will be rendered onto this
	 * {@link java.swing.JFrame}.
	 * 
	 * @since 1.0
	 */
	private final Animation animation;

	/**
	 * The thread that runs the animation.
	 * 
	 * @see #animation
	 * @since 1.0
	 */
	private Thread animationThread;

	/**
	 * Creates a standard animation frame with a {@code width} of {@code 400} and
	 * height of {@code 400}. Uses a specified name and animation.
	 * 
	 * @param name      - name of frame
	 * @param animation - the animation displayed on frame
	 * @throws HeadlessException
	 * @since 1.0
	 */
	public AnimationFrame(String name, Animation animation) {
		super(name);
		this.animation = animation;
	}

	/**
	 * Begins animation thread.
	 * @since 1.0
	 */
	public void start() {
		add(this.animation);

		animationThread = new Thread(animation);
		animationThread.start();
	}

	/**
	 * Begins the animation thread with specified layout.
	 * @param layout - the specified layout
	 * @since 1.0
	 */
	public void start(String layout) {
		add(this.animation, layout);

		animationThread = new Thread(animation);
		animationThread.start();
	}

	/**
	 * Stops the animation.
	 * 
	 * @since 1.0
	 */
	public void stop() {
		animation.stop();
	}
}
