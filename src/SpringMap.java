
public class SpringMap extends GameMap {
	
	Path[][] path = new Path[BloonsRunner.HEIGHT / BloonsRunner.PATH_WIDTH][BloonsRunner.WIDTH / BloonsRunner.PATH_WIDTH];
	int[][] coordinates = new int[BloonsRunner.HEIGHT / BloonsRunner.PATH_WIDTH][BloonsRunner.WIDTH / BloonsRunner.PATH_WIDTH];
	String name;
	
	public SpringMap(String s) {
		super(s);
	}

	public int[][] getCoordinates() {
		return coordinates;
	}

	public Path[][] getPath() {
		return path;
	}

	public void setName(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}

}
