package edu.oregonstate.pongbros;

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

	int[][] coordinates = {{0, 4},{1, 4},{2, 4},{3, 4},{4, 4},{5, 4},{6, 4},{7, 4},{8, 4},{9, 4},{10, 4},{11, 4},{12, 4},{13, 4},{14, 4},{15, 4},{16, 4},{17, 4}};
	
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
