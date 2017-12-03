import java.awt.Color;
import java.awt.Graphics;

public abstract class Path {
	public static int BALLOON_PATH = 0;
	public static int SPRITE_PATH = 1;
	public static int SCENERY = 2;
	
	public abstract int getType();
	public abstract Color getColor();
}
