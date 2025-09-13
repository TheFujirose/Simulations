/**
 * 
 */
package math;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * 
 */
public class Vector2D extends Point2D implements Serializable {

	private static final long serialVersionUID = 1954086647524100391L;

	/**
	 * Creates a new {@code Vector2D} from the addition of both specified {@code Vector2D}.
	 * @param v1 - the first specified {@code Vector2D}
	 * @param v2 - the second specified {@code Vector2D}
	 * @return the sum of both specified {@code Vector2D}.
	 */
	public static Vector2D add(Point2D v1, Point2D v2) {
		Vector2D v3 = new Vector2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());
		return v3;
	}

	/**
	 * Creates a new {@code Vector2D} from the subtraction of both specified {@code Vector2D}.
	 * @param v1 - the first specified {@code Vector2D} as minuend
	 * @param v2 - the second specified {@code Vector2D} as subtrahend
	 * @return the difference of the first specified vector and the second as {@code Vector2D}
	 */
	public static Vector2D sub(Point2D v1, Point2D v2) {
		Vector2D v3 = new Vector2D(v1.getX() - v2.getX(), v1.getY() - v2.getY());
		return v3;
	}

	/**
	 * Creates a new {@code Vector2D} from the division of both specified {@code Vector2D}.
	 * @param v1 - the first specified {@code Vector2D} as Dividend
	 * @param v2 - the second specified {@code Vector2D} ad Divisor
	 * @return the quotient of the first and second {@code Vector2D}
	 */
	public static Vector2D div(Point2D v1, Point2D v2) {
		Vector2D v3 = new Vector2D(v1.getX() / v2.getX(), v1.getY()/ v2.getY()) ;
		return v3;
	}

	/**
	 * Creates a new {@code Vector2D} from the multiplication of both specified {@code Vector2D}
	 * @param v1 - the first specified {@code Vector2D}
	 * @param v2 - the second specified {@code Vector2D}
	 * @return the product of both {@code Vector2D}
	 */
	public static Vector2D mult(Point2D v1, Point2D v2) {
		Vector2D v3 = new Vector2D(v1.getX()/ v2.getX(), v1.getY() / v2.getY());
		return v3;
	}

	/**
	 * Creates a new {@code Vector2D} from the normal of the specified {@code Vector2D}
	 * @param v2 - the specified {@code Vector2D}
	 * @return a clone of the specified {@code Vector2D}, but normalized
	 */
	public static Vector2D normal(Vector2D v2) {
		Vector2D v3 = v2.clone();
		v3.normalize();
		return v3;
	}

	/**
	 * The x value of this vector
	 *
	 */
	private double x;

	/**
	 * The y value of this vector
	 */
	private double y;

	/**
	 * @param x - this vector's x value
	 * @param y - this vector's y value
	 */
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Adds the specified value to the location values of this {@code Vector2D}.
	 * @param num - the specified value to add
	 * @see #x
	 * @see #y
	 */
	public void add(double num) {
		x += num;
		y += num;
	}

	/**
	 * Subtracts the specified value from the location values of this {@code Vector2D}.
	 * @param num - the specified value to subtract
	 * @see #x
	 * @see #y
	 */
	public void sub(double num) {
		x -= num;
		y -= num;
	}

	/**
	 * Multiplies the specified value with the location values of this {@code Vector2D}.
	 * @param num - the specified value to multiply
	 * @see #x
	 * @see #y
	 */
	public void mult(double num) {
		x *= num;
		y *= num;
	}

	/**
	 * Divides the location values of this {@code Vector2D} by the specified value.
	 * @param num - the specified divisor
	 * @see #x
	 * @see #y
	 */
	public void div(double num) {
		x /= num;
		y /= num;
	}

	/**
	 * @return The magnitude of this {@code Vector2D}
	 */
	public double mag() {
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * Normalizes this {@code Vector2D}
	 */
	public void normalize() {
		double m = mag();
		if (m != 0) {
			div(m);
		}
	}

	/**
	 * Limits this {@code Vector2D} to the specified maximum magnitude
	 * @param max the specified maximum magnitude
	 */
	public void limit(double max) {
		if (mag() > max) {
			normalize();
			mult(max);
		}
	}
	
	
	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Vector2D clone() {
		Vector2D v3 = new Vector2D(x,y);
		return v3;
	}

	public void setX(double x) {
		this.x = x;
	} 

	public void setY(double y) {
		this.y = y;
	}

	public void addVector(Vector2D v2) {
		y += v2.y;
		x += v2.x;
	}
}
