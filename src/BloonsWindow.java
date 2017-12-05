import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class BloonsWindow extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	Timer t = new Timer(5, this);
	
	// Pregame buttons
	GameButton playButton = new GameButton(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2 + 60, 200, 60);;
	
	public BloonsWindow() {
		t.start();
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	public void actionPerformed(ActionEvent e) {
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
		// ------------------ POSTGAME CANVAS CODE ------------------ \\
		} else if (BloonsRunner.phase == "postgame") {
			
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
			
		} else if (BloonsRunner.phase == "postgame") {
			
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
			
		} else if (BloonsRunner.phase == "postgame") {
			
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
}
