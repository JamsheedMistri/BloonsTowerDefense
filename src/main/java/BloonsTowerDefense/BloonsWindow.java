package BloonsTowerDefense;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class BloonsWindow extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	// Amount of milliseconds between frames
	public static final int FPSDelay = 10;

	// Timer to keep track of everything
	Timer t = new Timer(FPSDelay, this);

	// Variables
	int mouseX = 0, mouseY = 0;
	String isSelectingMonkey = "no";
	String tip = "";

	// Buttons
	// Pregame
	GameButton easyButton = new GameButton(BloonsRunner.WIDTH / 2 - 275, BloonsRunner.HEIGHT / 2 + 120, 200, 60);
	GameButton mediumButton = new GameButton(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2 + 120, 200, 60);
	GameButton hardButton = new GameButton(BloonsRunner.WIDTH / 2 + 125, BloonsRunner.HEIGHT / 2 + 120, 200, 60);
	GameButton instructionsButton = new GameButton(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2, 200, 60);
	GameButton backButton = new GameButton(BloonsRunner.WIDTH / 2, BloonsRunner.HEIGHT / 2 + 120, 200, 60);
	// Game
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

	// Do this every 10ms
	public void actionPerformed(ActionEvent e) {
		if (BloonsRunner.phase.equals("game")) {
			// If it's game, move the bloons and summon new ones
			if (BloonsRunner.gamePhase.equals("game")) {
				for (int i = 0; i < BloonsRunner.currentBloons.length; i ++) {
					BloonsRunner.currentBloons[i].move(BloonsRunner.map.getCoordinates());
					if (BloonsRunner.currentBloons[i].needsToSummonNextBloon.equals("true") && i + 1 != BloonsRunner.currentBloons.length) {
						BloonsRunner.currentBloons[i + 1].initiate(BloonsRunner.map.getCoordinates());
					}
				}

				// End the game if you loose, end round if all bloons are dead
				if (BloonsRunner.health <= 0) {
					BloonsRunner.gamePhase = "lost";
					BloonsRunner.phase = "postgame";
				} else if (checkIfAllBloonsDead()) {
					endRound();
				}
			}

			// Tip management
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

	// Everything that gets painted goes here
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

			// Instructions Button
			// Hover color
			if (instructionsButton.hover) g.setColor(new Color(234, 182, 0));
			else g.setColor(new Color(249, 205, 54));
			// Draw
			g.fillRect(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2, 200, 60);
			// Button text
			g.setFont(new Font("Verdana", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			g.drawString("INSTRUCTIONS", BloonsRunner.WIDTH / 2 - 75, BloonsRunner.HEIGHT / 2 + 35);

			// Play Buttons
			// Hover color
			if (easyButton.hover) g.setColor(new Color(234, 182, 0));
			else g.setColor(new Color(249, 205, 54));
			// Draw
			g.fillRect(BloonsRunner.WIDTH / 2 - 325, BloonsRunner.HEIGHT / 2 + 120, 200, 60);
			// Button text
			g.setFont(new Font("Verdana", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			g.drawString("EASY MAP", BloonsRunner.WIDTH / 2 - 275, BloonsRunner.HEIGHT / 2 + 160);

			// Hover color
			if (mediumButton.hover) g.setColor(new Color(234, 182, 0));
			else g.setColor(new Color(249, 205, 54));
			// Draw
			g.fillRect(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2 + 120, 200, 60);
			// Button text
			g.setFont(new Font("Verdana", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			g.drawString("MEDIUM MAP", BloonsRunner.WIDTH / 2 - 65, BloonsRunner.HEIGHT / 2 + 160);

			// Hover color
			if (hardButton.hover) g.setColor(new Color(234, 182, 0));
			else g.setColor(new Color(249, 205, 54));
			// Draw
			g.fillRect(BloonsRunner.WIDTH / 2 + 125, BloonsRunner.HEIGHT / 2 + 120, 200, 60);
			// Button text
			g.setFont(new Font("Verdana", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			g.drawString("HARD MAP", BloonsRunner.WIDTH / 2 + 175, BloonsRunner.HEIGHT / 2 + 160);
			// ------------------ INSTRUCTIONS CANVAS CODE ------------------ \\
		} else if (BloonsRunner.phase.equals("instructions")) {
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
			g.setFont(new Font("Verdana", Font.PLAIN, 15));
			g.setColor(Color.BLACK);
			g.drawString("Welcome to Bloons Tower Defense, made by Jamsheed Mistri. In this game, you lead a team of monkeys with the one main goal", 10, 30);
			g.drawString("in mind of popping the enemy bloons. Each round, bloons spawn on a path, and your job is to place monkeys strategically", 10, 50);
			g.drawString("throughout the map in order to not let the bloons reach the end of the path. Each round gets progressively harder, with", 10, 70);
			g.drawString("different types of bloons with different layers. Red bloons have 1 layer, blue bloons have 2 layers, and there are many more,", 10, 90);
			g.drawString("such as lead bloons that have 9 layers. Every time you pop a bloon, you remove a layer, until it has 0 left. Each pop awards", 10, 110);
			g.drawString("you with $1, which can be used to purchase more monkeys. The monkeys are as follows: ", 10, 130);

			g.setFont(new Font("Verdana", Font.BOLD, 15));

			// Draw monkeys
			MonkeySprite.drawPreview(g, 20, 140);
			g.setColor(Color.BLACK);
			g.drawString("Monkey - Shoots one dart per second. Cost: $" + MonkeySprite.price, 70, 170);

			NinjaSprite.drawPreview(g, 20, 200);
			g.setColor(Color.BLACK);
			g.drawString("Ninja Monkey - Shoots two ninja stars per second. Each pop gives $2 instead of $1. Cost: $" + NinjaSprite.price, 70, 230);

			SuperMonkeySprite.drawPreview(g, 20, 260);
			g.setColor(Color.BLACK);
			g.drawString("Super Monkey - Shoots four darts per second. Cost: $" + SuperMonkeySprite.price, 70, 290);

			g.setFont(new Font("Verdana", Font.PLAIN, 15));

			g.drawString("There are 25 rounds, and three maps, each with different difficulty. You start with 50 health, and for each bloon that ", 10, 340);
			g.drawString("reaches the end of the path, your health decreases by the amount of layers that it had.", 10, 370);

			g.drawString("Can you save the monkeys from the bloon apocalypse?", 10, 420);

			// Back Button
			// Hover color
			if (backButton.hover) g.setColor(new Color(234, 182, 0));
			else g.setColor(new Color(249, 205, 54));
			// Draw
			g.fillRect(BloonsRunner.WIDTH / 2, BloonsRunner.HEIGHT / 2 + 120, 200, 60);
			// Button text
			g.setFont(new Font("Verdana", Font.PLAIN, 40));
			g.setColor(Color.WHITE);
			g.drawString("BACK", BloonsRunner.WIDTH / 2 + 43, BloonsRunner.HEIGHT / 2 + 165);
			// ------------------ INSTRUCTIONS CANVAS CODE ------------------ \\
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

			// Hover for starting round
			if (startRoundButton.hover && BloonsRunner.gamePhase.equals("pregame") && isSelectingMonkey == "no") {
				g.setColor(new Color(22, 195, 221));
				g.fillRect(BloonsRunner.WIDTH - 100, BloonsRunner.HEIGHT - 100, 100, 100);
			}

			// Start round button
			if (BloonsRunner.gamePhase.equals("pregame")) {
				g.setFont(new Font("Verdana", Font.BOLD, 15));
				g.setColor(Color.WHITE);
				g.drawString("START", (BloonsRunner.WIDTH - 100) + 20, (BloonsRunner.HEIGHT - 100) + 40);
			} else if (BloonsRunner.gamePhase.equals("game")) {
				g.setFont(new Font("Verdana", Font.BOLD, 14));
				g.setColor(Color.WHITE);
				g.drawString("PLAYING...", (BloonsRunner.WIDTH - 100) + 5, (BloonsRunner.HEIGHT - 100) + 40);
			}

			// Draw all bloons
			if (BloonsRunner.gamePhase.equals("game")) {
				for (int i = 0; i < BloonsRunner.currentBloons.length; i ++) {
					if (BloonsRunner.currentBloons[i].getCoordinates() != null && BloonsRunner.currentBloons[i].getCoordinates() != new int[]{-1, -1}) {
						g.setColor(BloonsRunner.currentBloons[i].getColor());
						g.fillOval((BloonsRunner.currentBloons[i].getCoordinates()[0] * BloonsRunner.PATH_WIDTH) + 15, (BloonsRunner.currentBloons[i].getCoordinates()[1] * BloonsRunner.PATH_WIDTH) + 10, BloonsRunner.PATH_WIDTH - 30, BloonsRunner.PATH_WIDTH - 20);
						g.fillOval((BloonsRunner.currentBloons[i].getCoordinates()[0] * BloonsRunner.PATH_WIDTH) + 22, (BloonsRunner.currentBloons[i].getCoordinates()[1] * BloonsRunner.PATH_WIDTH) + 37, 6, 6);
					}
				}
			}

			// Draw monkey if you're selecting it
			if (!isSelectingMonkey.equals("no")) {
				if (isSelectingMonkey.equals("MonkeySprite")) {
					if (checkIfCanPlaceMonkey()) g.setColor(new Color(255, 0, 0, 100));
					else g.setColor(new Color(0, 0, 0, 100));

					g.fillOval(mouseX - MonkeySprite.radius, mouseY - MonkeySprite.radius, MonkeySprite.radius * 2, MonkeySprite.radius * 2);
					MonkeySprite.drawPreview(g, mouseX - (BloonsRunner.PATH_WIDTH / 2), mouseY - (BloonsRunner.PATH_WIDTH / 2));
				} else if (isSelectingMonkey.equals("NinjaSprite")) {
					if (checkIfCanPlaceMonkey()) g.setColor(new Color(255, 0, 0, 100));
					else g.setColor(new Color(0, 0, 0, 100));

					g.fillOval(mouseX - NinjaSprite.radius, mouseY - NinjaSprite.radius, NinjaSprite.radius * 2, NinjaSprite.radius * 2);
					NinjaSprite.drawPreview(g, mouseX - (BloonsRunner.PATH_WIDTH / 2), mouseY - (BloonsRunner.PATH_WIDTH / 2));
				} else if (isSelectingMonkey.equals("SuperMonkeySprite")) {
					if (checkIfCanPlaceMonkey()) g.setColor(new Color(255, 0, 0, 100));
					else g.setColor(new Color(0, 0, 0, 100));

					g.fillOval(mouseX - SuperMonkeySprite.radius, mouseY - SuperMonkeySprite.radius, SuperMonkeySprite.radius * 2, SuperMonkeySprite.radius * 2);
					SuperMonkeySprite.drawPreview(g, mouseX - (BloonsRunner.PATH_WIDTH / 2), mouseY - (BloonsRunner.PATH_WIDTH / 2));
				}
			}

			// Draw shop
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

			// Draw each currently purchased monkey and its bullets
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

			// Draw hover in the shop
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
				if (mediumButton.hover) g.setColor(new Color(234, 182, 0));
				else g.setColor(new Color(249, 205, 54));
				// Draw
				g.fillRect(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2 + 120, 200, 60);
				// Button text
				g.setFont(new Font("Verdana", Font.PLAIN, 40));
				g.setColor(Color.WHITE);
				g.drawString("AGAIN?", BloonsRunner.WIDTH / 2 - 75, BloonsRunner.HEIGHT / 2 + 167);

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
				if (mediumButton.hover) g.setColor(new Color(234, 182, 0));
				else g.setColor(new Color(249, 205, 54));
				// Draw
				g.fillRect(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2 + 120, 200, 60);
				// Button text
				g.setFont(new Font("Verdana", Font.PLAIN, 40));
				g.setColor(Color.WHITE);
				g.drawString("AGAIN?", BloonsRunner.WIDTH / 2 - 75, BloonsRunner.HEIGHT / 2 + 167);
			}
		}
	}

	// When escape is pressed, stop selecting the current monkey
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (BloonsRunner.phase.equals("game")) {
			if (code == 27 && !isSelectingMonkey.equals("no")) {
				isSelectingMonkey = "no";
			}
		}

	}

	// Check for hovering on a button/monkey when the mouse moves
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getPoint().x;
		mouseY = e.getPoint().y;

		if (BloonsRunner.phase.equals("pregame")) {
			instructionsButton.hover = instructionsButton.checkCoordinates(mouseX, mouseY);
			easyButton.hover = easyButton.checkCoordinates(mouseX, mouseY);
			mediumButton.hover = mediumButton.checkCoordinates(mouseX, mouseY);
			hardButton.hover = hardButton.checkCoordinates(mouseX, mouseY);
		} else if (BloonsRunner.phase.equals("instructions")) {
			backButton.hover = backButton.checkCoordinates(mouseX, mouseY);
		} else if (BloonsRunner.phase.equals("game")) {
			startRoundButton.hover = startRoundButton.checkCoordinates(mouseX, mouseY);
			monkeySpriteButton.hover = monkeySpriteButton.checkCoordinates(mouseX, mouseY);
			ninjaSpriteButton.hover = ninjaSpriteButton.checkCoordinates(mouseX, mouseY);
			superMonkeySpriteButton.hover = superMonkeySpriteButton.checkCoordinates(mouseX, mouseY);
		} else if (BloonsRunner.phase.equals("postgame")) {
			mediumButton.hover = mediumButton.checkCoordinates(mouseX, mouseY);
		}
	}

	// Check for clicks on buttons and monkeys
	public void mouseClicked(MouseEvent e) {
		if (BloonsRunner.phase.equals("pregame")) {
			if (instructionsButton.checkCoordinates(mouseX, mouseY)) {
				BloonsRunner.phase = "instructions";
			} else if (easyButton.checkCoordinates(mouseX, mouseY)) {
				BloonsRunner.map = new GardenMap("Garden");
				BloonsRunner.phase = "game";
			} else if (mediumButton.checkCoordinates(mouseX, mouseY)) {
				BloonsRunner.map = new CornMap("Corn");
				BloonsRunner.phase = "game";
			} else if (hardButton.checkCoordinates(mouseX, mouseY)) {
				BloonsRunner.map = new SpringMap("Spring");
				BloonsRunner.phase = "game";
			}
		} else if (BloonsRunner.phase.equals("instructions")) {
			if (backButton.checkCoordinates(mouseX, mouseY)) {
				BloonsRunner.phase = "pregame";
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
				if (!checkIfCanPlaceMonkey()) {
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
			// Reset game
			if (mediumButton.checkCoordinates(mouseX, mouseY)) {
				BloonsRunner.phase = "pregame";
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

	// Start round
	public void startRound() {
		if (BloonsRunner.lastRound == BloonsRunner.round) {
			BloonsRunner.round ++;
			BloonsRunner.currentBloons = BloonsRunner.maps[BloonsRunner.round - 1];
			BloonsRunner.currentBloons[0].initiate(BloonsRunner.map.getCoordinates());
			BloonsRunner.gamePhase = "game";
		}
	}

	// End round
	public void endRound() {
		BloonsRunner.lastRound ++;
		BloonsRunner.money += 100 + (BloonsRunner.round * 10);
		BloonsRunner.gamePhase = "pregame";
		if (BloonsRunner.round == BloonsRunner.maps.length) {
			BloonsRunner.gamePhase = "won";
			BloonsRunner.phase = "postgame";
		}
	}

	// Pretty self explanatory
	public boolean checkIfAllBloonsDead() {
		for (int i = 0; i < BloonsRunner.currentBloons.length; i ++) {
			if (BloonsRunner.currentBloons[i].getCoordinates() == null || BloonsRunner.currentBloons[i].getCoordinates()[0] >= 0) return false;
		}
		return true;
	}

	// Check if a monkey is on a path, on the game selection stuff, or on another monkey.
	public boolean checkIfCanPlaceMonkey() {
		if (mouseX > BloonsRunner.WIDTH - 100 || mouseY > BloonsRunner.HEIGHT - 100) return true;
		for (int i = 0; i < BloonsRunner.map.getCoordinates().length; i ++) {
			int[] mouseCoords = new int[]{(int)(mouseX / BloonsRunner.PATH_WIDTH),(int)(mouseY / BloonsRunner.PATH_WIDTH)};
			if (BloonsRunner.map.getCoordinates()[i][0] == mouseCoords[0] && BloonsRunner.map.getCoordinates()[i][1] == mouseCoords[1]) return true;
		}

		for (MonkeySprite m : MonkeySprite.monkeys) {
			if ((mouseX > m.x && mouseX < m.x + BloonsRunner.PATH_WIDTH) && (mouseY > m.y && mouseY < m.y + BloonsRunner.PATH_WIDTH)) return true;
		}

		for (NinjaSprite m : NinjaSprite.monkeys) {
			if ((mouseX > m.x && mouseX < m.x + BloonsRunner.PATH_WIDTH) && (mouseY > m.y && mouseY < m.y + BloonsRunner.PATH_WIDTH)) return true;
		}

		for (SuperMonkeySprite m : SuperMonkeySprite.monkeys) {
			if ((mouseX > m.x && mouseX < m.x + BloonsRunner.PATH_WIDTH) && (mouseY > m.y && mouseY < m.y + BloonsRunner.PATH_WIDTH)) return true;
		}

		return false;
	}
	
	// Unused inherited methods
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void mouseDragged(MouseEvent e) {}
}
