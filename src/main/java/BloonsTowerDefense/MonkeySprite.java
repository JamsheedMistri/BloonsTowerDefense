package BloonsTowerDefense;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class MonkeySprite extends Sprite {
	public static int price = 100;
	public static final int radius = 120;
	public static int projectileDelay = 1000;
	public static ArrayList<MonkeySprite> monkeys = new ArrayList<MonkeySprite>();
	
	private int currentDelay = 1000;
	public ArrayList<DartProjectile> projectiles = new ArrayList<DartProjectile>();

	public MonkeySprite(int x, int y) {
		super(x, y);
	}

	public void shoot() {
		if (!BloonsRunner.gamePhase.equals("game")) return;
		if (currentDelay >= projectileDelay) {
			for (int i = 0; i < BloonsRunner.currentBloons.length; i ++) {
				if (BloonsRunner.currentBloons[i].getCoordinates() == null) continue;
				
				int xCoordOfBloon = (BloonsRunner.currentBloons[i].getCoordinates()[0] * BloonsRunner.PATH_WIDTH) + (BloonsRunner.PATH_WIDTH / 2);
				int yCoordOfBloon = (BloonsRunner.currentBloons[i].getCoordinates()[1] * BloonsRunner.PATH_WIDTH) + (BloonsRunner.PATH_WIDTH / 2);
				
				// Using distance formula to see if bloon is within range
				if (Math.sqrt(Math.pow(xCoordOfBloon - (x + BloonsRunner.PATH_WIDTH / 2), 2) + Math.pow(yCoordOfBloon - (y + BloonsRunner.PATH_WIDTH / 2), 2)) < radius) {
					// Initiate new projectile
					projectiles.add(new DartProjectile(x + (BloonsRunner.PATH_WIDTH / 2), y + (BloonsRunner.PATH_WIDTH / 2), xCoordOfBloon, yCoordOfBloon, 10));
					currentDelay = 0;
					return;
				}
			}
		} else {
			currentDelay += BloonsWindow.FPSDelay;
		}
	}
	
	public void projectileAction() {
		for (int p = 0; p < projectiles.size(); p ++) {
			projectiles.get(p).move();
			projectiles.get(p).popBloons();
			if (projectiles.get(p).reachedTarget()) {
				projectiles.remove(p);
			}
		}
	}
	
	public void drawProjectiles(Graphics g) {
		for (Projectile p : projectiles) {
			p.draw(g);
		}
	}

	public void draw(Graphics g) {
		g.setColor(new Color(127, 80, 10));
		g.fillOval(x + 5, y + 5, BloonsRunner.PATH_WIDTH - 10, BloonsRunner.PATH_WIDTH - 10);
		g.fillRect(x + (BloonsRunner.PATH_WIDTH / 2) - 1, y + (BloonsRunner.PATH_WIDTH / 2), 2, BloonsRunner.PATH_WIDTH / 2);
		g.setColor(Color.RED);
		g.fillRect((int)(x + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 10)) / 5)), (int)(y + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 10)) / 4)), (int)((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 10))) / 2, 8);
		g.setColor(Color.WHITE);
		g.fillOval(x + (BloonsRunner.PATH_WIDTH / 2) - 10, (int)(y + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 30)) / 2)), 4, 4);
		g.fillOval(x + (BloonsRunner.PATH_WIDTH / 2) + 10, (int)(y + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 30)) / 2)), 4, 4);
	}

	public static void drawPreview(Graphics g, int x, int y) {
		g.setColor(new Color(127, 80, 10));
		g.fillOval(x + 5, y + 5, BloonsRunner.PATH_WIDTH - 10, BloonsRunner.PATH_WIDTH - 10);
		g.fillRect(x + (BloonsRunner.PATH_WIDTH / 2) - 1, y + (BloonsRunner.PATH_WIDTH / 2), 2, BloonsRunner.PATH_WIDTH / 2);
		g.setColor(Color.RED);
		g.fillRect((int)(x + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 10)) / 5)), (int)(y + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 10)) / 4)), (int)((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 10))) / 2, 8);
		g.setColor(Color.WHITE);
		g.fillOval(x + (BloonsRunner.PATH_WIDTH / 2) - 10, (int)(y + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 30)) / 2)), 4, 4);
		g.fillOval(x + (BloonsRunner.PATH_WIDTH / 2) + 10, (int)(y + (BloonsRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BloonsRunner.PATH_WIDTH - 30)) / 2)), 4, 4);
	}
}
