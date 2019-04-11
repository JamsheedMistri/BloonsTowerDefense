package edu.oregonstate.pongbros;

public class GardenMap extends GameMap {
	String name;

	Tile[][] path = {
			{new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new CornTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new WoodGameTile(), new WoodGameTile()},
			{new CornTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new CornTile(), new CornTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new WoodGameTile(), new WoodGameTile()},
			{new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new WoodGameTile(), new WoodGameTile()},
			{new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new CornTile(), new CornTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new CornTile(), new DirtTile(), new FlowerTile(), new WoodGameTile(), new WoodGameTile()},
			{new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new CornTile(), new FlowerTile(), new DirtTile(), new CornTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new WoodGameTile(), new WoodGameTile()},
			{new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new CornTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new WoodGameTile(), new WoodGameTile()},
			{new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new FlowerTile(), new WoodGameTile(), new WoodGameTile()},
			{new FlowerTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new FlowerTile(), new FlowerTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new CornTile(), new CornTile(), new DirtTile(), new DirtTile(), new DirtTile(), new DirtTile(), new FlowerTile(), new WoodGameTile(), new WoodGameTile()},
			{new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new CornTile(), new FlowerTile(), new FlowerTile(), new CornTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new FlowerTile(), new CornTile(), new FlowerTile(), new WoodGameTile(), new WoodGameTile()},
			{new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new PlayGameTile(), new PlayGameTile()},
			{new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new LightWoodGameTile(), new PlayGameTile(), new PlayGameTile()}
	};

	int[][] coordinates = {{1, 0},{1, 1},{1, 2},{1, 3},{1, 4},{1, 5},{1, 6},{1, 7},{2, 7},{3, 7},{4, 7},{4, 6},{4, 5},{4, 4},{4, 3},{4, 2},{4, 1},{5, 1},{6, 1},{7, 1},{7, 2},{7, 3},{7, 4},{7, 5},{7, 6},{7, 7},{8, 7},{9, 7},{10, 7},{10, 6},{10, 5},{10, 4},{10, 3},{10, 2},{10, 1},{11, 1},{12, 1},{13, 1},{13, 2},{13, 3},{13, 4},{13, 5},{13, 6},{13, 7},{14, 7},{15, 7},{16, 7},{16, 6},{16, 5},{16, 4},{16, 3},{16, 2},{16, 1},{16, 0}};

	public GardenMap(String s) {
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
