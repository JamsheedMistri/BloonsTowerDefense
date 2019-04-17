package BloonsTowerDefense;

public class GameButton {
	int x, y, width, height;
	boolean hover = false;

	public GameButton(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean checkCoordinates(int mouseX, int mouseY) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
	}
}
