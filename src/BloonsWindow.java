import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BloonsWindow extends JPanel implements ActionListener, KeyListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	Timer t = new Timer(5, this);
	
	public BloonsWindow() {
		t.start();
		addKeyListener(this);
		GameButton gameButton = new GameButton();
		addMouseMotionListener(this);
		addMouseListener(gameButton);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, BloonsRunner.WIDTH, BloonsRunner.HEIGHT);
		
		g.setColor(Color.BLUE);
		g.fillRect(BloonsRunner.WIDTH / 2 - 100, BloonsRunner.HEIGHT / 2 - 30, 200, 60);
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
		
		
	}
}
