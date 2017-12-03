import java.awt.Color;
import java.awt.Graphics;

public class GrassPath extends Path {

	public int getType() {
		return Path.SPRITE_PATH;
	}

	public Color getColor() {
		return new Color(161, 244, 66);
	}
}
