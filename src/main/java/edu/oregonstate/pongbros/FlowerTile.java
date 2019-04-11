package edu.oregonstate.pongbros;

import java.awt.Color;
import java.awt.Graphics;

public class FlowerTile extends Tile {

	public int getType() {
		return Tile.SPRITE_PATH;
	}
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(22, 153, 10));
		g.fillRect(x, y, BloonsRunner.PATH_WIDTH, BloonsRunner.PATH_WIDTH);
		g.setColor(Color.YELLOW);
		g.fillOval(x + 5, y + 5, 5, 5);
		g.setColor(Color.ORANGE);
		g.fillOval(x + 15, y + 13, 5, 5);
		g.setColor(Color.RED);
		g.fillOval(x + 42, y + 43, 5, 5);
		g.setColor(Color.CYAN);
		g.fillOval(x + 34, y + 23, 5, 5);
		g.setColor(Color.GREEN);
		g.fillOval(x + 21, y + 35, 5, 5);
	}
}
