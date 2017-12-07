
public class Bloon {
	public String type;
	private int[] coordinates;
	public int layers;
	public int currentDelay;
	public String needsToSummonNextBloon;
	
	// Red: 1 layer, speed 2000
	// Blue: 2 layers, speed 1600
	// Green: 3 layers, speed 1200
	// Yellow: 4 layers, speed 800
	// Pink: 5 layers, speed 400
	// Cyan (small): 6 layers, speed 300
	// Black (small): 7 layers, speed 300
	// Clay: 8 layers, speed 1500
	// Lead: 9 layers, speed 2000
	
	public Bloon(String s) {
		switch (s) {
			case ("red"): layers = 1;
			case ("blue"): layers = 2;
			case ("green"): layers = 3;
			case ("yellow"): layers = 4;
			case ("pink"): layers = 5;
			case ("cyan"): layers = 6;
			case ("black"): layers = 7;
			case ("clay"): layers = 8;
			case ("lead"): layers = 9;
		}
	}
	
	public int getSpeed() {
		switch (layers) {
			case (1): return 2000;
			case (2): return 1600;
			case (3): return 1200;
			case (4): return 800;
			case (5): return 400;
			case (6): return 300;
			case (7): return 300;
			case (8): return 1500;
			case (9): return 2000;
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
			
			if (needsToSummonNextBloon == null) {
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
