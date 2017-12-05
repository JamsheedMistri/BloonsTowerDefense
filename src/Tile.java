import java.awt.Color;
import java.awt.Graphics;

public abstract class Tile {
	public static int BALLOON_PATH = 0;
	public static int SPRITE_PATH = 1;
	public static int SCENERY = 2;
	public static int GAME_TILE = 3;
	
	public abstract int getType();
	public abstract void draw(Graphics g, int x, int y);
}
