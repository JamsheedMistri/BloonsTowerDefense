import java.awt.Color;
import java.awt.Graphics;

public class NinjaSprite extends Sprite {
	public static final int price = 250;

	public NinjaSprite(int x, int y) {
		super(x, y);
	}

	public void shoot() {

	}

	public void draw(Graphics g) {
		g.setColor(new Color(40, 40, 40));
		g.fillOval(x + 5, y + 5, BloonsRunner.PATH_WIDTH - 10, BloonsRunner.PATH_WIDTH - 10);
		g.fillRect(x + (BloonsRunner.PATH_WIDTH / 2) - 1, y + (BloonsRunner.PATH_WIDTH / 2), 2, BloonsRunner.PATH_WIDTH / 2);
		g.setColor(Color.WHITE);
		g.fillRect((int)(x + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 10)) / 5)), (int)(y + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 10)) / 4)), (int)((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 10))) / 2, 8);
		g.setColor(Color.BLACK);
		g.fillOval(x + (BloonsRunner.PATH_WIDTH / 2) - 10, (int)(y + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 30)) / 2)), 4, 4);
		g.fillOval(x + (BloonsRunner.PATH_WIDTH / 2) + 10, (int)(y + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 30)) / 2)), 4, 4);
	}

	public static void drawPreview(Graphics g, int x, int y) {
		g.setColor(new Color(40, 40, 40));
		g.fillOval(x + 5, y + 5, BloonsRunner.PATH_WIDTH - 10, BloonsRunner.PATH_WIDTH - 10);
		g.fillRect(x + (BloonsRunner.PATH_WIDTH / 2) - 1, y + (BloonsRunner.PATH_WIDTH / 2), 2, BloonsRunner.PATH_WIDTH / 2);
		g.setColor(Color.WHITE);
		g.fillRect((int)(x + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 10)) / 5)), (int)(y + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 10)) / 4)), (int)((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 10))) / 2, 8);
		g.setColor(Color.BLACK);
		g.fillOval(x + (BloonsRunner.PATH_WIDTH / 2) - 10, (int)(y + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 30)) / 2)), 4, 4);
		g.fillOval(x + (BloonsRunner.PATH_WIDTH / 2) + 10, (int)(y + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 30)) / 2)), 4, 4);
	}
}
