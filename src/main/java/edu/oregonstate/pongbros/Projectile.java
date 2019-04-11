package edu.oregonstate.pongbros;

import java.awt.Graphics;

public abstract class Projectile {
	int x, y, xTarget, yTarget;
	double xVelocity, yVelocity;
	
	public Projectile(int xStart, int yStart, int xTarget, int yTarget, double velocity) {
		this.x = xStart;
		this.y = yStart;
		this.xTarget = xTarget;
		this.yTarget = yTarget;
		
		int xDistance = xTarget - xStart;
		int yDistance = yTarget - yStart;
		double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
		this.xVelocity = velocity * xDistance / distance;
		this.yVelocity = velocity * yDistance / distance;
	}
	
	public abstract void move();
	public abstract void draw(Graphics g);
}
