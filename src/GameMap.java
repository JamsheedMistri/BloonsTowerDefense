
public abstract class GameMap {
	
	public GameMap(String s) {
		setName(s);
	}
	
	public abstract int[][] getCoordinates();
	public abstract Tile[][] getPath();
	public abstract void setName(String s);
	public abstract String getName();
}
