package BloonsTowerDefense;

import java.awt.Color;
import java.awt.Graphics;

public class WoodGameTile extends Tile {
	public int getType() {
		return Tile.GAME_TILE;
	}
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(109, 63, 26));
		g.fillRect(x, y, BloonsRunner.PATH_WIDTH, BloonsRunner.PATH_WIDTH);
	}
}
