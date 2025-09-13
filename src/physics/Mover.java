/**
 * 
 */
package physics;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import math.Vector2D;

/**
 * 
 */
public class Mover extends Ellipse2D {


	/**
	 * The overall width of the {@code Mover}.
	 */
	protected double width;
	
	/**
	 * The overall height of the {@code Mover}.
	 */
	protected double height;
	
	/**
	 * The {@code (x,y)} location of the {@code Mover}. 
	 */
	protected Vector2D location;

	/**
	 * The 2D velocity of the {@code Mover}.
	 */
	protected Vector2D velocity;

	/**
	 * The 2D acceleration of the {@code Mover}.
	 */
	protected Vector2D acceleration;

	/**
	 * The color of this {@code Mover}.
	 */
	protected Color color;
	/**
	 * Creates a {@code Mover} with specified {@code (x,y)} position, width, and height.
	 * The {@code Mover} will have a velocity and acceleration of {@code (0,0)}
	 * @param x - the specified x position
	 * @param y - the specified y position
	 * @param w - the specified width
	 * @param h - the specified height
	 */
	public Mover(double x, double y, double w, double h) {
		location = new Vector2D(x,y);
		width = w;
		height = h;
		acceleration = new Vector2D(0,0);
		velocity = new Vector2D(0,0);
	}

	@Override
	public Rectangle2D getBounds2D() {
		return new Rectangle2D.Double(getX(),getY(), width, height);
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getX() {
		return location.getX();
	}

	@Override
	public double getY() {
		return location.getY();
	}

	@Override
	public boolean isEmpty() {
		return (width == 0 && height == 0);
	}

	@Override
	public void setFrame(double x, double y, double w, double h) {
		location.setLocation(x, y);
		width = w;
		height = h;
	}

	public Vector2D getLocation() {	
		return this.location;
	}

	public void setAcceleration(Vector2D dir) {
		acceleration.setX(dir.getX());
		acceleration.setY(dir.getY());
	}

	public void update() {
		velocity.addVector(acceleration);
		location.addVector(velocity);
		
		acceleration.mult(0);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}