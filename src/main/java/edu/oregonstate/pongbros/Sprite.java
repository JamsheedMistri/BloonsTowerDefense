package edu.oregonstate.pongbros;

import java.awt.Graphics;

public abstract class Sprite {
	protected int x;
	protected int y;
	
	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public abstract void shoot();
	public abstract void draw(Graphics g);
}
