package animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Defines a class of frame-by-frame visuals.
 *
 * @author Carson Fujita
 * @since 1.0
 */
public abstract class Animation extends JPanel  implements Animated {

	private static final long serialVersionUID = -5288955862125670480L;

	/**
	 * A lock object for synchronization.
	 * @since 1.0
	 */
	private final Object lock = new Object();

	/**
	 * Determines if this {@code Animation} is running. <br/>
	 * {@code true} if running; {@code false} if not.
	 * 
	 * @since 1.0
	 */
	private volatile boolean running = false;

	/**
	 * Determines if this {@code Animation} is paused. <br/>
	 * {@code true} if paused; {@code false} if not.
	 * 
	 * @since 1.0
	 */
	private volatile boolean paused = false;

	
	/**
	 * The duration in milliseconds this {@code Animation} will sleep
	 * for per render. 
	 * <br/>
	 * Defaulted to aim for 60fps.
	 * @see #render(Graphics2D)
	 * @since 1.0
	 */
	private long sleep_duration = (long) 16.67; 

	/**
	 * The frames per second value of this {@value Animation}.
	 */
	private volatile float fps;
	
	/**
	 * The previous time stamp from rendering the animation.
	 * Used for calculating elapsed time and frames per second
	 * @see #update()
	 * @see #fps
	 * @since 1.0
	 */
	private long previousTime = System.nanoTime();
	
	/**
	 * The amount of frames printed since the {@link #previousTime}.
	 * Used for calculating frames per secound in {@link #update()}
	 * @see #update()
	 * @see #previousTime
	 * @since 1.0
	 */
	private int frames;
	
	/**
	 * Creates an empty {@code Animation}.
	 * @since 1.0
	 */
	public Animation() {
		super();
	}

	/**
	 * Creates a empty animation with a specified layout.
	 * @param layout - specified layout
	 * @since 1.0
	 */
	public Animation(LayoutManager layout) {
		super(layout);
	}
	
	/**
	 * Creates a empty animation. Determines if double buffered.
	 * @param isDoubleBuffered - true if double buffered
	 * @since 1.0
	 */
	public Animation(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	/**
	 * Creates an empty {@code Animation} with a specified layout.
	 * Determines if double buffered.
	 * @param layout - specified layout
	 * @param isDoubleBuffered - true if double buffered
	 * @since 1.0
	 */
	public Animation(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}	
	
	
	/**
	 * Renders a frame of animation onto the specified {@code Graphics2D} object.
	 * 
	 * @param g - the specified {@code Graphics2D} object.
	 */
	public abstract void render(Graphics2D g);

	
	/**
	 * Updates animation parameters and state.
	 * 
	 * @since 1.0
	 */
	public void update() {
		long current = System.nanoTime();
		long elapsed = current - previousTime;
		
		//more than a second
		if(elapsed >= 1_000_000_000L) { 
			//convert to seconds
			fps = (float) frames * 1_000_000_000L / elapsed;
			frames = 0;
			previousTime = current;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		render((Graphics2D) g);
		frames++;
	}
	
	@Override
	public void stop() {
		synchronized (lock) {
			running = false;
		}
	}

	@Override
	public void run() {
		running = true;
		while (running) {
			synchronized (lock) {
				try {
					if (paused && running) {
						while (paused && running) {
							lock.wait();
						}
					} else {
						Thread.sleep(sleep_duration); // hopefully 60fps
					}
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
					return;
				}
				if(running) {
					update();
					SwingUtilities.invokeLater(this::repaint);
				}
			}
		}
	}

	/**
	 * Pauses the animation.
	 * @since 1.0
	 * @see #paused
	 * @see #resume()
	 */
	public void pause() {
		synchronized (lock) {
			paused = true;
		}
	}

	/**
	 * Resumes the animation.
	 * @since 1.0
	 * @see #paused
	 * @see #pause()
	 */
	public void resume() {
		synchronized (lock) {
			if (!paused) {
				lock.notifyAll(); // wake all threads
				paused = false;
			}
		}
	}
	
	/**
	 * Sets the {@link Animation#sleep_duration} to target the specified
	 * frames per second.
	 * @param target_fps - the specified frames per second
	 * @see #sleep_duration
	 * @since 1.0
	 */
	public void setFPS(int target_fps) {
		sleep_duration = (long) 1000 / target_fps;
	}

	/**
	 * @return the last calculated FPS value from the animation.
	 * @since 1.0
	 */
	public float getFPS() {
		return fps;
	}
}