import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;

public class BloonsRunner {
	
	public static int WIDTH = 1000, HEIGHT = 550, PATH_WIDTH = 50;
	public static String phase = "pregame";
	public static int points = 0;
	public static GameMap map;
	
	public static void main(String[] args) {
		JFrame f = new JFrame("Bloons Tower Defense");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BloonsWindow w = new BloonsWindow();
		f.add(w);
		f.setSize(WIDTH, HEIGHT);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

}
