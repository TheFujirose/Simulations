package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;

import animation.Animated;
import animation.AnimationFrame;
import math.Vector2D;
import noise.NoiseGenerator;
import physics.Mover;

public class Animation1 implements Animated {

	public static int AMOUNT = 5;
	public static int WIDTH = 800;
	public static int HEIGHT = 400;
	public static int SIZE = 5;
	private Thread runner;
	private AnimationFrame animationFrame;
	private Mover list[] = new Mover[Animation1.AMOUNT];
	private NoiseGenerator noise;
	
	public Animation1() {
		animationFrame = new AnimationFrame("First Test", Animation1.WIDTH, Animation1.HEIGHT, this);
		noise = new NoiseGenerator();
		for(int i = 0; i < Animation1.AMOUNT; i++) {
			Color color = new Color((float) (Math.random()),(float) (Math.random()),(float) (Math.random())); //random
			list[i] = new Mover(Animation1.WIDTH * Math.random(),Animation1.HEIGHT * Math.random(),Animation1.SIZE, Animation1.SIZE);
			list[i].setColor(color);
		}
	}

	@Override
	public void run() {
		while(true) {
			animationFrame.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void start() {
		if( runner == null) {
			runner = new Thread(this);
			runner.start();
			animationFrame.setVisible(true);
		}
	}

	@Override
	public void stop() {
		if(runner != null) {
			runner.interrupt();
			runner = null;
		}
	}


	@Override
	public void paint(Graphics2D g) {
		Point mouse = MouseInfo.getPointerInfo().getLocation();
		Vector2D dir; //direction to mouse
		for(Mover m: list) {
			g.setPaint(m.getColor());
			dir = Vector2D.sub(mouse,m.getLocation());
			dir.normalize();
			dir.mult(Math.abs(noise.noise(HEIGHT, WIDTH))*0.5);
			m.setAcceleration(dir);
			m.update();
			g.draw(m);
		}
	}

	@Override
	public void setFrame(AnimationFrame animationFrame) {
		this.animationFrame = animationFrame;
	}
	
	public static void main (String[] args) {
		new Animation1().start();    // Create a Main frame
	} // main method

}
