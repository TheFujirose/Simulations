package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JLabel;

import animation.Animation;
import animation.AnimationFrame;
import math.Vector2D;
import noise.NoiseGenerator;
import physics.Mover;

public class Animation2 extends Animation {
	private static final long serialVersionUID = -4566726150376732219L;
	public static int AMOUNT = 100;
	public static int WIDTH = 1000;
	public static int HEIGHT = 800;
	public static int SIZE = 10;

	private Mover list[] = new Mover[AMOUNT];
	private NoiseGenerator noise;
	private AnimationFrame frame;
	private JLabel labelFPS;
	public Animation2() {
		super();
		labelFPS = new JLabel("test");
		frame = new AnimationFrame("test2", this);
		frame.add(labelFPS, BorderLayout.NORTH);
		
		frame.setSize(WIDTH, HEIGHT);
		noise = new NoiseGenerator();
		for (int i = 0; i < AMOUNT; i++) {
			Color color = new Color((float) (Math.random()), (float) (Math.random()), (float) (Math.random())); // random
			list[i] = new Mover(WIDTH * Math.random(), HEIGHT * Math.random(), SIZE, SIZE);
			list[i].setColor(color);
		}

	}

	@Override
	public void start() {
		frame.start(BorderLayout.CENTER);
		frame.setVisible(true);
	}

	@Override
	public void render(Graphics2D g) {
		for (Mover m : list) {
			g.setPaint(m.getColor());
			g.fill(m);
		}
	}

	@Override
	public void update() {
		super.update();
		// TODO Auto-generated method stub
		Point mouse = MouseInfo.getPointerInfo().getLocation();
		Vector2D dir; // direction to mouse
		for (Mover m : list) {
			dir = Vector2D.sub(mouse, m.getLocation());
			dir.normalize();
			dir.mult(Math.abs(noise.noise(m.getX(), m.getY())));
			m.setAcceleration(Vector2D.add(m.getAcceleration(), dir));

			m.update();
		}
		labelFPS.setText(Float.toString(getFPS()));
	}

	public static void main(String[] args) {
		Animation2 animation = new Animation2();
		animation.start();

	}

}
