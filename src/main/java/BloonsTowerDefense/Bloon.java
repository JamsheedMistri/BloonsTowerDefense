package BloonsTowerDefense;

import java.awt.Color;

public class Bloon {
	private int[] coordinates;
	public int layers;
	public int currentDelay;
	public String needsToSummonNextBloon = "not started";
	
	// Red: 1 layer, speed 1000
	// Blue: 2 layers, speed 900
	// Green: 3 layers, speed 800
	// Yellow: 4 layers, speed 700
	// Pink: 5 layers, speed 600
	// Cyan (small): 6 layers, speed 500
	// Black (small): 7 layers, speed 400
	// Clay: 8 layers, speed 1000
	// Lead: 9 layers, speed 1500
	
	public Bloon(String s) {
		layers=-1;
		if (s.equals("red")) layers = 1;
		if (s.equals("blue")) layers = 2;
		if (s.equals("green")) layers = 3;
		if (s.equals("yellow")) layers = 4;
		if (s.equals("pink")) layers = 5;
		if (s.equals("cyan")) layers = 6;
		if (s.equals("black")) layers = 7;
		if (s.equals("clay")) layers = 8;
		if (s.equals("lead")) layers = 9;
	}
	
	public Color getColor() {
		switch (layers) {
			case (1): return Color.RED;
			case (2): return Color.BLUE;
			case (3): return Color.GREEN;
			case (4): return Color.YELLOW;
			case (5): return Color.PINK;
			case (6): return Color.CYAN;
			case (7): return Color.BLACK;
			case (8): return new Color(224, 155, 76);
			case (9): return Color.GRAY;
			default: return null;
		}
	}
	
	public int getSpeed() {
		switch (layers) {
			case (1): return 750;
			case (2): return 650;
			case (3): return 550;
			case (4): return 450;
			case (5): return 350;
			case (6): return 250;
			case (7): return 150;
			case (8): return 1000;
			case (9): return 1500;
			default: return -1;
		}
	}
	
	public int[] getCoordinates() {
		return coordinates;
	}
	
	public void initiate(int[][] coordinatesParam) {
		if (coordinates == null) {
			coordinates = coordinatesParam[0];
			return;
		}
	}
	
	public void move(int[][] coordinatesParam) {
		if (coordinates == null) return;
		
		currentDelay += BloonsWindow.FPSDelay;
		if (currentDelay >= getSpeed()) {
			currentDelay = 0;
			
			if (needsToSummonNextBloon == "not started") {
				needsToSummonNextBloon = "true";
			} else if (needsToSummonNextBloon.equals("true")) {
				needsToSummonNextBloon = "false";
			}
			
			for (int i = 0; i < coordinatesParam.length; i ++) {
				if (coordinatesParam[i] == coordinates) {
					if (i + 1 == coordinatesParam.length) {
						BloonsRunner.health -= layers;
						coordinates = new int[] {-1, -1};
					} else {
						coordinates = coordinatesParam[i + 1];
					}
					return;
				}
			}
		}
	}
	
	public void pop() {
		layers --;
		BloonsRunner.money ++;
		if (layers == 0) coordinates = new int[]{-1, -1};
	}
}
