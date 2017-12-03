import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BloonsWindow extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;

	Timer t = new Timer(5, this);
	int x = 0, y = 0, vx = 0, vy = 0, w = 30, h = 30;
	
	public BloonsWindow() {
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (x + vx >= 0 && x + vx <= BloonsRunner.WIDTH - w) x += vx;
		if (y + vy >= 0 && y + vy <= BloonsRunner.HEIGHT - h) y += vy;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, w, h);
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == 38) vy = -5;
		else if (code == 40) vy = 5;
		
		if (code == 37) vx = -5;
		else if (code == 39) vx = 5;
		
	}

	public void keyReleased(KeyEvent e) {
		vx = 0;
		vy = 0;
	}
	
}
