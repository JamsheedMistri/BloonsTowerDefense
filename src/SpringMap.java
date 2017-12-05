
public class SpringMap extends GameMap {
	String name;
	
	Tile[][] path = {
			{new GrassTile(), new TreeTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new TreeTile(), new GrassTile(), new GrassTile(), new GrassTile(), new TreeTile(), new TreeTile(), new GrassTile(), new GrassTile(), new GrassTile(), new TreeTile(), new GrassTile(), new GrassTile(), new WoodGameTile(), new WoodGameTile()},
			{new GrassTile(), new GrassTile(), new GrassTile(), new TreeTile(), new GrassTile(), new TreeTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new TreeTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new WoodGameTile(), new WoodGameTile()},
			{new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new TreeTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new TreeTile(), new WoodGameTile(), new WoodGameTile()},
			{new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new WoodGameTile(), new WoodGameTile()},
			{new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new WoodGameTile(), new WoodGameTile()},
			{new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new WoodGameTile(), new WoodGameTile()},
			{new GrassTile(), new TreeTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new TreeTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new WoodGameTile(), new WoodGameTile()},
			{new TreeTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new TreeTile(), new GrassTile(), new GrassTile(), new GrassTile(), new TreeTile(), new TreeTile(), new GrassTile(), new GrassTile(), new GrassTile(), new TreeTile(), new GrassTile(), new GrassTile(), new WoodGameTile(), new WoodGameTile()},
			{new GrassTile(), new GrassTile(), new GrassTile(), new TreeTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new GrassTile(), new WoodGameTile(), new WoodGameTile()},
			{new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new PlayGameTile(), new PlayGameTile()},
			{new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new PlayGameTile(), new PlayGameTile()}
	};

	int[][] coordinates = {{0, 5},{1, 5},{2, 5},{3, 5},{4, 5},{5, 5},{6, 5},{7, 5},{8, 5},{9, 5},{10, 5},{11, 5},{12, 5},{13, 5},{14, 5},{15, 5},{16, 5},{17, 5}};
	
	public SpringMap(String s) {
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
