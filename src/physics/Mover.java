/**
 * 
 */
package physics;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import math.Vector2D;

/**
 * a Class the defines a movable ball with acceleration, velocity, 
 * location as well as it's defined shape and color. It's usable in animations and can be printed
 * directly to {@link java.awt.Graphics} objects (without it's defined color).
 * <br/>
 * The vector values of any {@code Mover} will not change unless {@link Mover#update()} is invoked.
 * 
 * @since 1.0
 */
public class Mover extends Ellipse2D {

	/**
	 * Adjustable velocity limit for all Movers
	 * @since 1.0
	 */
	public static int LIMIT = 5;
	
	/**
	 * The overall width of the {@code Mover}.
	 * @since 1.0
	 */
	protected double width;
	
	/**
	 * The overall height of the {@code Mover}.
	 * @since 1.0
	 */
	protected double height;
	
	/**
	 * The mass of this {@code Mover}.
	 * @since 1.0
	 */
	protected float mass;
		
	/**
	 * The {@code (x,y)} location of the {@code Mover}. 
	 * @since 1.0
	 */
	protected Vector2D location;

	/**
	 * The 2D velocity of the {@code Mover}.
	 * @since 1.0
	 */
	protected Vector2D velocity;

	/**
	 * The 2D acceleration of the {@code Mover}.
	 * @since 1.0
	 */
	protected Vector2D acceleration;

	/**
	 * The color of this {@code Mover}.
	 * @since 1.0
	 */
	protected Color color;
	
	/**
	 * Creates a {@code Mover} with specified {@code (x,y)} position, width, and height.
	 * The {@code Mover} will have a velocity and acceleration of {@code (0,0)} with a mass
	 * of 0.
	 * @param x - the specified x position
	 * @param y - the specified y position
	 * @param w - the specified width
	 * @param h - the specified height
	 * @since 1.0
	 */
	public Mover(double x, double y, double w, double h) {
		location = new Vector2D(x,y);
		width = w;
		height = h;
		acceleration = new Vector2D(0,0);
		velocity = new Vector2D(0,0);
		mass = 0;
	}
	
	/**
	 * Creates a {@code Mover} with specified {@code (x,y)} position, width, height and mass.
	 * The {@code Mover} will have a velocity and acceleration of {@code (0,0)}
	 * @param x - the specified x position
	 * @param y - the specified y position
	 * @param w - the specified width
	 * @param h - the specified height
	 * @param m - the specified mass
	 * @since 1.0
	 */
	public Mover(double x, double y, double w, double h, float m) {
		location = new Vector2D(x,y);
		width = w;
		height = h;
		acceleration = new Vector2D(0,0);
		velocity = new Vector2D(0,0);
		mass = m;
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

	/**
	 * Determines if this {@code Mover} has no volume.
	 * @return true if empty
	 * @since 1.0
	 */
	@Override
	public boolean isEmpty() {
		return (width == 0 && height == 0);
	}

	/**
	 * Sets the frame of this {@code Mover} to the specified {@code (x,y)} 
	 * location and the specified width/height.
	 * @param x - the specified x value
	 * @param y - the specified y value
	 * @param w - the specified width
	 * @param h - the specified height
	 * @since 1.0
	 */
	@Override
	public void setFrame(double x, double y, double w, double h) {
		location.setLocation(x, y);
		width = w;
		height = h;
	}

	/**
	 * Returns the location value of this {@code Mover}.
	 * @return the location of this {@code Mover}
	 * @since 1.0
	 */
	public Vector2D getLocation() {	
		return this.location;
	}

	/**
	 * Sets the acceleration by value for the next update.
	 * @param v - the acceleration to set.
	 * @see #update()
	 * @since 1.0
	 */
	public void setAcceleration(Vector2D v) {
		acceleration.setX(v.getX());
		acceleration.setY(v.getY());
	}

	/**
	 * Checks if it's {@code #location} values is within the width height bounds.
	 * If not in bound this {@code mover} will negate the x-velocity or 
	 * y-velocity—depending on which edge it passed—and resets it's position to the max bound.
	 * @param width the specified bound width
	 * @param height the specified bound height
	 * @since 1.0
	 */
	public void checkEdges(int width, int height) {
		if(location.getX() > width) {
			location.setX(width);
			velocity.negateX();
		} else if(location.getX() < 0) {
			location.setX(0);
			velocity.negateX();
		}
		
		if(location.getY() > height) {
			location.setY(height);
			velocity.negateY();
		}
	}
	
	/**
	 * Updates this {@code Mover}'s vector values through it's
	 * {@link #acceleration} , {@link #LIMIT}, and {@link #velocity}.
	 * Then resets the acceleration back to {@code 0}
	 * @since 1.0
	 */
	public void update() {
		velocity.addVector(acceleration);
		velocity.limit(LIMIT);
		location.addVector(velocity);
		
		acceleration.mult(0);
	}
	
	/**
	 * Applies a force by value (attraction or not) to this {@code Mover}'s
	 * vector values.
	 * @param f - the force specified
	 * @since 1.0
	 */
	public void applyForce(Vector2D f) {
		Vector2D force = f.clone();
		force.div(mass);
		acceleration.addVector(f);
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Creates a deep-clone of this {@code Mover's} acceleration
	 * @return this {@code Mover}'s acceleration value
	 * @since 1.0
	 */
	public Point2D getAcceleration() {
		return acceleration.clone();
	}

}