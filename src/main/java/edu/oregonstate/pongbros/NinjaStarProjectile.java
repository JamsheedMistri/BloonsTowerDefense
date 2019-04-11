package edu.oregonstate.pongbros;

import java.awt.Color;
import java.awt.Graphics;

public class NinjaStarProjectile extends Projectile {

	public NinjaStarProjectile(int xStart, int yStart, int xTarget, int yTarget, double velocity) {
		super(xStart, yStart, xTarget, yTarget, velocity);
	}

	@Override
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	
	public void popBloons() {
		if (reachedTarget()) {
			for (int i = 0; i < BloonsRunner.currentBloons.length; i ++) {
				if (BloonsRunner.currentBloons[i].getCoordinates() == null) continue;
				
				int xCoordOfBloon = (BloonsRunner.currentBloons[i].getCoordinates()[0] * BloonsRunner.PATH_WIDTH) + (BloonsRunner.PATH_WIDTH / 2);
				int yCoordOfBloon = (BloonsRunner.currentBloons[i].getCoordinates()[1] * BloonsRunner.PATH_WIDTH) + (BloonsRunner.PATH_WIDTH / 2);
				
				if (xTarget == xCoordOfBloon && yTarget == yCoordOfBloon) {
					BloonsRunner.money ++;
					BloonsRunner.currentBloons[i].pop();
				}
			}
		}
	}
	
	public boolean reachedTarget() {
		if (xVelocity < 0) {
			if (x >= xTarget) return false;
		} else if (x <= xTarget) return false;
		
		if (yVelocity <= 0) {
			if (y >= yTarget) return false;
		} else if (y <= yTarget) return false;
		
		return true;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillPolygon(new int[]{x, x - 5, x + 5}, new int[]{y - 5, y + 5, y + 5}, 3);
	}
}
