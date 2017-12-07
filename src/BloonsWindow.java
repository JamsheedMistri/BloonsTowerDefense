import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

public class BloonsWindow extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	public static final int FPSDelay = 10;
	
	Timer t = new Timer(FPSDelay, this);
	
	// Pregame buttons
	GameButton playButton = new GameButton(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2 + 60, 200, 60);
	GameButton startRoundButton = new GameButton(BloonsRunner.WIDTH - 100, BloonsRunner.HEIGHT - 100, 100, 100);
	
	public BloonsWindow() {
		t.start();
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (BloonsRunner.gamePhase == "game" && BloonsRunner.phase == "game") {
			for (int i = 0; i < BloonsRunner.currentBloons.length; i ++) {
				BloonsRunner.currentBloons[i].move(BloonsRunner.map.getCoordinates());
				if (BloonsRunner.currentBloons[i].needsToSummonNextBloon.equals("true") && i + 1 != BloonsRunner.currentBloons.length) {
					BloonsRunner.currentBloons[i + 1].initiate(BloonsRunner.map.getCoordinates());
				}
			}
			if (BloonsRunner.health == 0) {
				BloonsRunner.gamePhase = "lost";
				BloonsRunner.phase = "postgame";
			}
			if (checkIfAllBloonsDead()) {
				endRound();
			}
		}
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// ------------------ PREGAME CANVAS CODE ------------------ \\
		if (BloonsRunner.phase == "pregame") {
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
		} else if (BloonsRunner.phase == "game") {
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
			g.drawString("Money: " + BloonsRunner.money, 410, (BloonsRunner.HEIGHT - 100) + 25);
			
			if (startRoundButton.hover && BloonsRunner.gamePhase == "pregame") {
				g.setColor(new Color(22, 195, 221));
				g.fillRect(BloonsRunner.WIDTH - 100, BloonsRunner.HEIGHT - 100, 100, 100);
			}
			
			if (BloonsRunner.gamePhase == "pregame") {
				g.setFont(new Font("Verdana", Font.BOLD, 15));
				g.setColor(Color.WHITE);
				g.drawString("START", (BloonsRunner.WIDTH - 100) + 20, (BloonsRunner.HEIGHT - 100) + 40);
			} else if (BloonsRunner.gamePhase == "game") {
				g.setFont(new Font("Verdana", Font.BOLD, 14));
				g.setColor(Color.WHITE);
				g.drawString("PLAYING...", (BloonsRunner.WIDTH - 100) + 5, (BloonsRunner.HEIGHT - 100) + 40);
			}
			
			if (BloonsRunner.gamePhase == "game") {
				for (int i = 0; i < BloonsRunner.currentBloons.length; i ++) {
					if (BloonsRunner.currentBloons[i].getCoordinates() != null && BloonsRunner.currentBloons[i].getCoordinates() != new int[]{-1, -1}) {
						g.setColor(BloonsRunner.currentBloons[i].getColor());
						g.fillOval((BloonsRunner.currentBloons[i].getCoordinates()[0] * BloonsRunner.PATH_WIDTH) + 15, (BloonsRunner.currentBloons[i].getCoordinates()[1] * BloonsRunner.PATH_WIDTH) + 10, BloonsRunner.PATH_WIDTH - 30, BloonsRunner.PATH_WIDTH - 20);
						g.fillOval((BloonsRunner.currentBloons[i].getCoordinates()[0] * BloonsRunner.PATH_WIDTH) + 22, (BloonsRunner.currentBloons[i].getCoordinates()[1] * BloonsRunner.PATH_WIDTH) + 37, 6, 6);
					}
				}
			}
		// ------------------ POSTGAME CANVAS CODE ------------------ \\
		} else if (BloonsRunner.phase == "postgame") {
			if (BloonsRunner.gamePhase == "won") {
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
				
			} else if (BloonsRunner.gamePhase == "lost") {
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
	}

	public void keyReleased(KeyEvent e) {

	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
		int x = e.getPoint().x;
		int y = e.getPoint().y;
		
		if (BloonsRunner.phase == "pregame") {
			playButton.hover = playButton.checkCoordinates(x, y);
		} else if (BloonsRunner.phase == "game") {
			startRoundButton.hover = startRoundButton.checkCoordinates(x, y);
		} else if (BloonsRunner.phase == "postgame") {
			playButton.hover = playButton.checkCoordinates(x, y);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		int x = e.getPoint().x;
		int y = e.getPoint().y;
		
		if (BloonsRunner.phase == "pregame") {
			if (playButton.checkCoordinates(x, y)) {
				BloonsRunner.map = new SpringMap("Spring");
				BloonsRunner.phase = "game";
			}
		} else if (BloonsRunner.phase == "game") {
			if (BloonsRunner.gamePhase == "pregame") {
				if (startRoundButton.checkCoordinates(x, y)) {
					startRound();
				}
			}
		} else if (BloonsRunner.phase == "postgame") {
			if (playButton.checkCoordinates(x, y)) {
				BloonsRunner.map = new SpringMap("Spring");
				BloonsRunner.phase = "game";
				BloonsRunner.gamePhase = "pregame";
				BloonsRunner.round = 0;
				BloonsRunner.currentBloons = null;
				BloonsRunner.maps = new Bloon[][]{
					// Round 1 -- 20 Red
					{new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red")},
					// Round 2 -- 30 Red
					{new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red")},
					// Round 3 -- 20 Red, 5 Blue
					{new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue")},
					// Round 4 -- 30 Red, 15 Blue
					{new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue")},
					// Round 5 -- 5 Red, 25 Blue
					{new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue")},
					// Round 6 -- 15 Red, 15 Blue, 4 Green
					{new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green")},
					// Round 7 -- 20 Red, 25 Blue, 5 Green
					{new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green")},
					// Round 8 -- 10 Red, 20 Blue, 14 Green
					{new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green")},
					// Round 9 -- 30 Green
					{new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green")},
					// Round 10 -- 102 Blue
					{new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue")},
					// Round 11 -- 10 Red, 10 Blue, 12 Green, 2 Yellow
					{new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("yellow"), new Bloon("yellow")},
					// Round 12 -- 15 Blue, 10 Green, 5 Yellow
					{new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow")},
					// Round 13 -- 100 Red, 23 Green, 4 Yellow
					{new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"),  new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow")},
					// Round 14 -- 50 Red, 15 Blue, 10 Green, 9 Yellow
					{new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"),  new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("blue"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow")},
					// Round 15 -- 20 Red, 12 Green, 5 Yellow, 3 Pink
					{new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("red"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("green"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("yellow"), new Bloon("pink"), new Bloon("pink"), new Bloon("pink")}
				};
				BloonsRunner.lastRound = 0;
				BloonsRunner.health = 50;
				BloonsRunner.money = 0;
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
		BloonsRunner.money += BloonsRunner.round * 100;
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
}
