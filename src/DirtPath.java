import java.awt.Color;
import java.awt.Graphics;

public class DirtPath extends Path {

	public int getType() {
		return Path.BALLOON_PATH;
	}

	public Color getColor() {
		return new Color(255, 244, 191);
	}
}
