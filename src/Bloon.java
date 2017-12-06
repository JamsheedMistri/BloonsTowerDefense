
public abstract class Bloon {
	public String type;
	private int layers;
	private int[] coordinates;
	public int delay;
	public int currentDelay;
	public String needsToSummonNextBloon;
	
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
		if (currentDelay >= delay) {
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
		BloonsRunner.money ++;
		layers --;
		if (layers == 0) {
			coordinates = new int[] {-1, -1};
		}
	}
}
