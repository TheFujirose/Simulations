/**
 * 
 */
package animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 *  Renders the animation.
 */
public class AnimationFrame extends JFrame {

	private static final long serialVersionUID = -3611290876909079204L;
	
	/**
	 * A screen required to prevent flashing issues with Java animation
	 */
	private BufferedImage offscreen;
	
	/**
	 * An optional background image
	 */
	private BufferedImage background;
	
	/**
	 * a buffered graphics of {@link AnimationFrame#offscreen} for rendering the animation
	 */
	private Graphics2D bufferGraphics;
	
	/**
	 * The animation to render.
	 */
	private Animated animation;
	
	/**
	 * @throws HeadlessException
	 */
	public AnimationFrame(String name, int width, int height, Animated animation){
		super(name);
		setSize(width, height);
		this.animation = animation;
		this.animation.setFrame(this);
		
		//Set the rendering hints for vector graphics
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Offscreen needed to prevent flashing
		offscreen = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		
		//buffer
		bufferGraphics = (Graphics2D) offscreen.createGraphics();
		bufferGraphics.setRenderingHints(rh);
	}	

	@Override
	public void paint(Graphics g) {
		if(background != null) {
			bufferGraphics.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		}
		animation.paint(bufferGraphics);
		g.drawImage(offscreen, 0,0, this);
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
}
