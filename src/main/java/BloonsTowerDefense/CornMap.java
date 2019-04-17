package BloonsTowerDefense;

public class CornMap extends GameMap {
	String name;
	
	Tile[][] path = {
			{new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new WoodGameTile(), new WoodGameTile()},
			{new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new WoodGameTile(), new WoodGameTile()},
			{new CornTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new WoodGameTile(), new WoodGameTile()},
			{new CornTile(), new DirtTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new WoodGameTile(), new WoodGameTile()},
			{new CornTile(), new DirtTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new WoodGameTile(), new WoodGameTile()},
			{new CornTile(), new DirtTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new WoodGameTile(), new WoodGameTile()},
			{new CornTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new WoodGameTile(), new WoodGameTile()},
			{new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new WoodGameTile(), new WoodGameTile()},
			{new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new CornTile(), new WoodGameTile(), new WoodGameTile()},
			{new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new PlayGameTile(), new PlayGameTile()},
			{new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new PlayGameTile(), new PlayGameTile()}
	};

	int[][] coordinates = {{17, 2},{16, 2},{15, 2},{14, 2},{13, 2},{12, 2},{11, 2},{10, 2},{9, 2},{8, 2},{7, 2},{6, 2},{5, 2},{4, 2},{3, 2},{2, 2},{1, 2},{1, 3},{1, 4},{1, 5},{1, 6},{2, 6},{3, 6},{4, 6},{5, 6},{6, 6},{7, 6},{8, 6},{9, 6},{10, 6},{11, 6},{12, 6},{13, 6},{14, 6},{15, 6},{16, 6},{17, 6}};
	
	public CornMap(String s) {
		super(s);
	}

	public int[][] getCoordinates() {
		return coordinates;
	}

	public Tile[][] getPath() {
		return path;
	}

	public void setName(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}

}
