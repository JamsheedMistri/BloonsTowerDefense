import java.awt.Color;
import java.awt.Graphics;

public class TreePath extends Path {

	public int getType() {
		return Path.SCENERY;
	}

	public Color getColor() {
		return new Color(22, 153, 10);
	}
}
