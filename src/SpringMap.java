
public class SpringMap extends GameMap {
	String name;
	
	Path[][] path = {
			{new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath()},
			{new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath()},
			{new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath()},
			{new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath()},
			{new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath()},
			{new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath(), new DirtPath()},
			{new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath()},
			{new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath()},
			{new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath()},
			{new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath()},
			{new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath(), new GrassPath()}
	};

	int[][] coordinates = {{0, 5},{1, 5},{2, 5},{3, 5},{4, 5},{5, 5},{6, 5},{7, 5},{8, 5},{9, 5},{10, 5},{11, 5},{12, 5},{13, 5},{14, 5},{15, 5},{16, 5},{17, 5},{18, 5},{19, 5}};
	
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
