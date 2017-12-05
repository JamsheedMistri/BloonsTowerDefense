import java.awt.Color;
import java.awt.Graphics;

public class PlayGameTile extends Tile {
	public int getType() {
		return Tile.GAME_TILE;
	}
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(81, 231, 255));
		g.fillRect(x, y, BloonsRunner.PATH_WIDTH, BloonsRunner.PATH_WIDTH);
	}
}
