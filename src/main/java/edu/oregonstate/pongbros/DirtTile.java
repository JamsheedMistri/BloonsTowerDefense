package edu.oregonstate.pongbros;

import java.awt.Color;
import java.awt.Graphics;

public class DirtTile extends Tile {

	public int getType() {
		return Tile.BALLOON_PATH;
	}

	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(255, 244, 191));
		g.fillRect(x, y, BloonsRunner.PATH_WIDTH, BloonsRunner.PATH_WIDTH);
	}
}
