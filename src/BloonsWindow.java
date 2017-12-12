import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class BloonsWindow extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	public static final int FPSDelay = 10;

	Timer t = new Timer(FPSDelay, this);

	int mouseX = 0, mouseY = 0;
	String isSelectingMonkey = "no";
	String tip = "";

	// Pregame buttons
	GameButton playButton = new GameButton(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2 + 60, 200, 60);
	GameButton startRoundButton = new GameButton(BloonsRunner.WIDTH - 100, BloonsRunner.HEIGHT - 100, 100, 100);
	GameButton monkeySpriteButton = new GameButton(BloonsRunner.WIDTH - 100, 0, 50, 50);
	GameButton ninjaSpriteButton = new GameButton(BloonsRunner.WIDTH - 100, BloonsRunner.PATH_WIDTH, 50, 50);
	GameButton superMonkeySpriteButton = new GameButton(BloonsRunner.WIDTH - 100, BloonsRunner.PATH_WIDTH * 2, 50, 50);

	public BloonsWindow() {
		t.start();
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (BloonsRunner.phase.equals("game")) {
			if (BloonsRunner.gamePhase.equals("game")) {
				for (int i = 0; i < BloonsRunner.currentBloons.length; i ++) {
					BloonsRunner.currentBloons[i].move(BloonsRunner.map.getCoordinates());
					if (BloonsRunner.currentBloons[i].needsToSummonNextBloon.equals("true") && i + 1 != BloonsRunner.currentBloons.length) {
						BloonsRunner.currentBloons[i + 1].initiate(BloonsRunner.map.getCoordinates());
					}
				}

				if (BloonsRunner.health <= 0) {
					BloonsRunner.gamePhase = "lost";
					BloonsRunner.phase = "postgame";
				} else if (checkIfAllBloonsDead()) {
					endRound();
				}
			}

			if (!isSelectingMonkey.equals("no")) {
				tip = "Hit Escape [ESC] to cancel selecting.";
			} else if (BloonsRunner.round == 0) {
				if (BloonsRunner.money > 0) {
					tip = "You currently have $" + BloonsRunner.money + ". Either buy something from the shop or hit play on the bottom right!";
				} else {
					tip = "Hit play on the bottom right to start the round!";
				}
			} else {
				tip = "";
			}
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// ------------------ PREGAME CANVAS CODE ------------------ \\
		if (BloonsRunner.phase.equals("pregame")) {
			// Background
			g.setColor(new Color(81, 253, 255));
			g.fillRect(0, 0, BloonsRunner.WIDTH, BloonsRunner.HEIGHT);

			// Dirt
			g.setColor(new Color(97, 72, 37));
			g.fillRect(0, BloonsRunner.HEIGHT - 50, BloonsRunner.WIDTH, 50);

			// Grass
			g.setColor(new Color(112, 215, 88));
			g.fillRect(0, BloonsRunner.HEIGHT - 65, BloonsRunner.WIDTH, 15);

			// Title text
			g.setFont(new Font("Verdana", Font.BOLD, 65));
			g.setColor(new Color(116, 116, 116));
			g.drawString("BLOONS TOWER", 200, 100);

			g.setFont(new Font("Verdana", Font.BOLD, 120));
			g.setColor(new Color(196, 13, 13));
			g.drawString("DEFENSE", 190, 200);

			g.setFont(new Font("Verdana", Font.PLAIN, 20));
			g.setColor(Color.BLACK);
			g.drawString("BY JAMSHEED MISTRI", 390, 250);

			// Play Button
			// Hover color
			if (playButton.hover) g.setColor(new Color(234, 182, 0));
			else g.setColor(new Color(249, 205, 54));
			// Draw
			g.fillRect(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2 + 60, 200, 60);
			// Button text
			g.setFont(new Font("Verdana", Font.PLAIN, 50));
			g.setColor(Color.WHITE);
			g.drawString("PLAY", BloonsRunner.WIDTH / 2 - 60, BloonsRunner.HEIGHT / 2 + 107);
			// ------------------ GAME CANVAS CODE ------------------ \\
		} else if (BloonsRunner.phase.equals("game")) {
			for (int row = 0; row < BloonsRunner.HEIGHT / BloonsRunner.PATH_WIDTH; row ++) {
				for (int col = 0; col < BloonsRunner.WIDTH / BloonsRunner.PATH_WIDTH; col ++) {
					BloonsRunner.map.getPath()[row][col].draw(g, col * BloonsRunner.PATH_WIDTH, row * BloonsRunner.PATH_WIDTH);
				}
			}

			// Statistics display
			g.setFont(new Font("Verdana", Font.PLAIN, 15));
			g.setColor(Color.BLACK);
			g.drawString("Round: " + BloonsRunner.round, 10, (BloonsRunner.HEIGHT - 100) + 25);
			g.drawString("Health: " + BloonsRunner.health, 210, (BloonsRunner.HEIGHT - 100) + 25);
			g.drawString("Money: $" + BloonsRunner.money, 410, (BloonsRunner.HEIGHT - 100) + 25);
			if (!tip.equals("")) {
				g.setFont(new Font("Verdana", Font.BOLD, 15));
				g.drawString("[TIP] " + tip, 10, (BloonsRunner.HEIGHT - 70) + 25);
			}

			if (startRoundButton.hover && BloonsRunner.gamePhase.equals("pregame") && isSelectingMonkey == "no") {
				g.setColor(new Color(22, 195, 221));
				g.fillRect(BloonsRunner.WIDTH - 100, BloonsRunner.HEIGHT - 100, 100, 100);
			}

			if (BloonsRunner.gamePhase.equals("pregame")) {
				g.setFont(new Font("Verdana", Font.BOLD, 15));
				g.setColor(Color.WHITE);
				g.drawString("START", (BloonsRunner.WIDTH - 100) + 20, (BloonsRunner.HEIGHT - 100) + 40);
			} else if (BloonsRunner.gamePhase.equals("game")) {
				g.setFont(new Font("Verdana", Font.BOLD, 14));
				g.setColor(Color.WHITE);
				g.drawString("PLAYING...", (BloonsRunner.WIDTH - 100) + 5, (BloonsRunner.HEIGHT - 100) + 40);
			}

			if (BloonsRunner.gamePhase.equals("game")) {
				for (int i = 0; i < BloonsRunner.currentBloons.length; i ++) {
					if (BloonsRunner.currentBloons[i].getCoordinates() != null && BloonsRunner.currentBloons[i].getCoordinates() != new int[]{-1, -1}) {
						g.setColor(BloonsRunner.currentBloons[i].getColor());
						g.fillOval((BloonsRunner.currentBloons[i].getCoordinates()[0] * BloonsRunner.PATH_WIDTH) + 15, (BloonsRunner.currentBloons[i].getCoordinates()[1] * BloonsRunner.PATH_WIDTH) + 10, BloonsRunner.PATH_WIDTH - 30, BloonsRunner.PATH_WIDTH - 20);
						g.fillOval((BloonsRunner.currentBloons[i].getCoordinates()[0] * BloonsRunner.PATH_WIDTH) + 22, (BloonsRunner.currentBloons[i].getCoordinates()[1] * BloonsRunner.PATH_WIDTH) + 37, 6, 6);
					}
				}
			}

			if (!isSelectingMonkey.equals("no")) {
				if (isSelectingMonkey.equals("MonkeySprite")) {
					if (checkIfMouseIsOnPath()) g.setColor(new Color(255, 0, 0, 100));
					else g.setColor(new Color(0, 0, 0, 100));

					g.fillOval(mouseX - MonkeySprite.radius, mouseY - MonkeySprite.radius, MonkeySprite.radius * 2, MonkeySprite.radius * 2);
					MonkeySprite.drawPreview(g, mouseX - (BloonsRunner.PATH_WIDTH / 2), mouseY - (BloonsRunner.PATH_WIDTH / 2));
				} else if (isSelectingMonkey.equals("NinjaSprite")) {
					if (checkIfMouseIsOnPath()) g.setColor(new Color(255, 0, 0, 100));
					else g.setColor(new Color(0, 0, 0, 100));

					g.fillOval(mouseX - NinjaSprite.radius, mouseY - NinjaSprite.radius, NinjaSprite.radius * 2, NinjaSprite.radius * 2);
					NinjaSprite.drawPreview(g, mouseX - (BloonsRunner.PATH_WIDTH / 2), mouseY - (BloonsRunner.PATH_WIDTH / 2));
				} else if (isSelectingMonkey.equals("SuperMonkeySprite")) {
					if (checkIfMouseIsOnPath()) g.setColor(new Color(255, 0, 0, 100));
					else g.setColor(new Color(0, 0, 0, 100));

					g.fillOval(mouseX - SuperMonkeySprite.radius, mouseY - SuperMonkeySprite.radius, SuperMonkeySprite.radius * 2, SuperMonkeySprite.radius * 2);
					SuperMonkeySprite.drawPreview(g, mouseX - (BloonsRunner.PATH_WIDTH / 2), mouseY - (BloonsRunner.PATH_WIDTH / 2));
				}
			}

			MonkeySprite.drawPreview(g, BloonsRunner.WIDTH - (BloonsRunner.PATH_WIDTH * 2), 0);
			g.setFont(new Font("Verdana", Font.BOLD, 10));
			g.setColor(Color.WHITE);
			g.drawString("$" + MonkeySprite.price, (BloonsRunner.WIDTH - 50) + 5, 25);

			NinjaSprite.drawPreview(g, BloonsRunner.WIDTH - (BloonsRunner.PATH_WIDTH * 2), BloonsRunner.PATH_WIDTH);
			g.setFont(new Font("Verdana", Font.BOLD, 10));
			g.setColor(Color.WHITE);
			g.drawString("$" + NinjaSprite.price, (BloonsRunner.WIDTH - 50) + 5, BloonsRunner.PATH_WIDTH + 25);

			SuperMonkeySprite.drawPreview(g, BloonsRunner.WIDTH - (BloonsRunner.PATH_WIDTH * 2), BloonsRunner.PATH_WIDTH * 2);
			g.setFont(new Font("Verdana", Font.BOLD, 10));
			g.setColor(Color.WHITE);
			g.drawString("$" + SuperMonkeySprite.price, (BloonsRunner.WIDTH - 50) + 5, (BloonsRunner.PATH_WIDTH * 2) + 25);

			for (MonkeySprite m : MonkeySprite.monkeys) {
				m.draw(g);
				m.drawProjectiles(g);
				m.shoot();
				m.projectileAction();
			}

			for (NinjaSprite m : NinjaSprite.monkeys) {
				m.draw(g);
				m.drawProjectiles(g);
				m.shoot();
				m.projectileAction();
			}

			for (SuperMonkeySprite m : SuperMonkeySprite.monkeys) {
				m.draw(g);
				m.drawProjectiles(g);
				m.shoot();
				m.projectileAction();
			}

			if (BloonsRunner.money < MonkeySprite.price) {
				g.setColor(new Color(0, 0, 0, 150));
				g.fillRect(BloonsRunner.WIDTH - 100, 0, 50, 50);
			} else if (monkeySpriteButton.hover && isSelectingMonkey == "no") {
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(BloonsRunner.WIDTH - 100, 0, 50, 50);
			}

			if (BloonsRunner.money < NinjaSprite.price) {
				g.setColor(new Color(0, 0, 0, 150));
				g.fillRect(BloonsRunner.WIDTH - 100, BloonsRunner.PATH_WIDTH, 50, 50);
			} else if (ninjaSpriteButton.hover && isSelectingMonkey == "no") {
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(BloonsRunner.WIDTH - 100, BloonsRunner.PATH_WIDTH, 50, 50);
			}

			if (BloonsRunner.money < SuperMonkeySprite.price) {
				g.setColor(new Color(0, 0, 0, 150));
				g.fillRect(BloonsRunner.WIDTH - 100, BloonsRunner.PATH_WIDTH * 2, 50, 50);
			} else if (superMonkeySpriteButton.hover && isSelectingMonkey == "no") {
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(BloonsRunner.WIDTH - 100, BloonsRunner.PATH_WIDTH * 2, 50, 50);
			}

			// ------------------ POSTGAME CANVAS CODE ------------------ \\
		} else if (BloonsRunner.phase.equals("postgame")) {
			if (BloonsRunner.gamePhase.equals("won")) {
				// Background
				g.setColor(new Color(81, 253, 255));
				g.fillRect(0, 0, BloonsRunner.WIDTH, BloonsRunner.HEIGHT);

				// Dirt
				g.setColor(new Color(97, 72, 37));
				g.fillRect(0, BloonsRunner.HEIGHT - 50, BloonsRunner.WIDTH, 50);

				// Grass
				g.setColor(new Color(112, 215, 88));
				g.fillRect(0, BloonsRunner.HEIGHT - 65, BloonsRunner.WIDTH, 15);

				// Text
				g.setFont(new Font("Verdana", Font.BOLD, 120));
				g.setColor(new Color(196, 13, 13));
				g.drawString("YOU WON!", 180, 200);

				// Play Button
				// Hover color
				if (playButton.hover) g.setColor(new Color(234, 182, 0));
				else g.setColor(new Color(249, 205, 54));
				// Draw
				g.fillRect(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2 + 60, 200, 60);
				// Button text
				g.setFont(new Font("Verdana", Font.PLAIN, 40));
				g.setColor(Color.WHITE);
				g.drawString("AGAIN?", BloonsRunner.WIDTH / 2 - 75, BloonsRunner.HEIGHT / 2 + 107);

			} else if (BloonsRunner.gamePhase.equals("lost")) {
				// Background
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, BloonsRunner.WIDTH, BloonsRunner.HEIGHT);

				// Text
				g.setFont(new Font("Verdana", Font.BOLD, 120));
				g.setColor(Color.WHITE);
				g.drawString("YOU LOST", 180, 200);

				// Play Button
				// Hover color
				if (playButton.hover) g.setColor(new Color(234, 182, 0));
				else g.setColor(new Color(249, 205, 54));
				// Draw
				g.fillRect(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2 + 60, 200, 60);
				// Button text
				g.setFont(new Font("Verdana", Font.PLAIN, 40));
				g.setColor(Color.WHITE);
				g.drawString("AGAIN?", BloonsRunner.WIDTH / 2 - 75, BloonsRunner.HEIGHT / 2 + 107);
			}
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (BloonsRunner.phase.equals("game")) {
			if (code == 27 && !isSelectingMonkey.equals("no")) {
				isSelectingMonkey = "no";
			}
		}

	}

	public void keyReleased(KeyEvent e) {

	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getPoint().x;
		mouseY = e.getPoint().y;

		if (BloonsRunner.phase.equals("pregame")) {
			playButton.hover = playButton.checkCoordinates(mouseX, mouseY);
		} else if (BloonsRunner.phase.equals("game")) {
			startRoundButton.hover = startRoundButton.checkCoordinates(mouseX, mouseY);
			monkeySpriteButton.hover = monkeySpriteButton.checkCoordinates(mouseX, mouseY);
			ninjaSpriteButton.hover = ninjaSpriteButton.checkCoordinates(mouseX, mouseY);
			superMonkeySpriteButton.hover = superMonkeySpriteButton.checkCoordinates(mouseX, mouseY);
		} else if (BloonsRunner.phase.equals("postgame")) {
			playButton.hover = playButton.checkCoordinates(mouseX, mouseY);
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (BloonsRunner.phase.equals("pregame")) {
			if (playButton.checkCoordinates(mouseX, mouseY)) {
				//	BloonsRunner.map = new SpringMap("Spring");
				//	BloonsRunner.map = new CornMap("Corn");
				BloonsRunner.map = new GardenMap("Garden");
				BloonsRunner.phase = "game";
			}
		} else if (BloonsRunner.phase.equals("game")) {
			if (BloonsRunner.gamePhase.equals("pregame")) {
				if (startRoundButton.checkCoordinates(mouseX, mouseY) && isSelectingMonkey == "no") {
					startRound();
				}
			} else if (BloonsRunner.gamePhase.equals("game")) {

			}

			if (isSelectingMonkey.equals("no")) {
				if (MonkeySprite.price <= BloonsRunner.money && monkeySpriteButton.checkCoordinates(mouseX, mouseY)) {
					isSelectingMonkey = "MonkeySprite";
				} else if (NinjaSprite.price <= BloonsRunner.money && ninjaSpriteButton.checkCoordinates(mouseX, mouseY)) {
					isSelectingMonkey = "NinjaSprite";
				} else if (SuperMonkeySprite.price <= BloonsRunner.money && superMonkeySpriteButton.checkCoordinates(mouseX, mouseY)) {
					isSelectingMonkey = "SuperMonkeySprite";
				}
			} else {
				if (!checkIfMouseIsOnPath()) {
					if (isSelectingMonkey.equals("MonkeySprite")) {
						MonkeySprite.monkeys.add(new MonkeySprite(mouseX - (BloonsRunner.PATH_WIDTH / 2), mouseY - (BloonsRunner.PATH_WIDTH / 2)));
						BloonsRunner.money -= MonkeySprite.price;
					} else if (isSelectingMonkey.equals("NinjaSprite")) {
						NinjaSprite.monkeys.add(new NinjaSprite(mouseX - (BloonsRunner.PATH_WIDTH / 2), mouseY - (BloonsRunner.PATH_WIDTH / 2)));
						BloonsRunner.money -= NinjaSprite.price;
					} else if (isSelectingMonkey.equals("SuperMonkeySprite")) {
						SuperMonkeySprite.monkeys.add(new SuperMonkeySprite(mouseX - (BloonsRunner.PATH_WIDTH / 2), mouseY - (BloonsRunner.PATH_WIDTH / 2)));
						BloonsRunner.money -= SuperMonkeySprite.price;
					}
					isSelectingMonkey = "no";
				}
			}
		} else if (BloonsRunner.phase.equals("postgame")) {
			if (playButton.checkCoordinates(mouseX, mouseY)) {
				BloonsRunner.phase = "game";
				BloonsRunner.gamePhase = "pregame";
				BloonsRunner.round = 0;
				BloonsRunner.currentBloons = null;
				BloonsRunner.maps = BloonsRunner.getBloonTracks();
				BloonsRunner.lastRound = 0;
				BloonsRunner.health = 50;
				BloonsRunner.money = 100;
				isSelectingMonkey = "no";
				MonkeySprite.monkeys = new ArrayList<MonkeySprite>();
				NinjaSprite.monkeys = new ArrayList<NinjaSprite>();
				SuperMonkeySprite.monkeys = new ArrayList<SuperMonkeySprite>();
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void startRound() {
		if (BloonsRunner.lastRound == BloonsRunner.round) {
			BloonsRunner.round ++;
			BloonsRunner.currentBloons = BloonsRunner.maps[BloonsRunner.round - 1];
			BloonsRunner.currentBloons[0].initiate(BloonsRunner.map.getCoordinates());
			BloonsRunner.gamePhase = "game";
		}
	}

	public void endRound() {
		BloonsRunner.lastRound ++;
		BloonsRunner.money += 100 + (BloonsRunner.round * 10);
		BloonsRunner.gamePhase = "pregame";
		if (BloonsRunner.round == BloonsRunner.maps.length) {
			BloonsRunner.gamePhase = "won";
			BloonsRunner.phase = "postgame";
		}
	}

	public boolean checkIfAllBloonsDead() {
		for (int i = 0; i < BloonsRunner.currentBloons.length; i ++) {
			if (BloonsRunner.currentBloons[i].getCoordinates() == null || BloonsRunner.currentBloons[i].getCoordinates()[0] >= 0) return false;
		}
		return true;
	}

	public boolean checkIfMouseIsOnPath() {
		if (mouseX > BloonsRunner.WIDTH - 100 || mouseY > BloonsRunner.HEIGHT - 100) return true;
		for (int i = 0; i < BloonsRunner.map.getCoordinates().length; i ++) {
			int[] mouseCoords = new int[]{(int)(mouseX / BloonsRunner.PATH_WIDTH),(int)(mouseY / BloonsRunner.PATH_WIDTH)};
			if (BloonsRunner.map.getCoordinates()[i][0] == mouseCoords[0] && BloonsRunner.map.getCoordinates()[i][1] == mouseCoords[1]) return true;
		}
		return false;
	}
}
