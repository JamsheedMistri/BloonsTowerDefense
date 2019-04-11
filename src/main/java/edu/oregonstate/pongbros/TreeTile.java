package edu.oregonstate.pongbros;

import java.awt.Color;
import java.awt.Graphics;

public class TreeTile extends Tile {

	public int getType() {
		return Tile.SPRITE_PATH;
	}
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(22, 153, 10));
		g.fillRect(x, y, BloonsRunner.PATH_WIDTH, BloonsRunner.PATH_WIDTH);
	}
}
