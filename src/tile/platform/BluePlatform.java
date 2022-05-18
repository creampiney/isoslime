package tile.platform;

import tile.base.Changable;
import tile.base.Tile;
import tile.base.Walkable;

public class BluePlatform extends Tile implements Walkable, Changable{

	public BluePlatform() {
		super("blueplatform", "Blue Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new BlueEmptyPlatform();
	}

}
