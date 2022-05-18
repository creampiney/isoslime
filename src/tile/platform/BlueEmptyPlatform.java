package tile.platform;

import tile.base.Changable;
import tile.base.Tile;
import tile.base.Walkable;

public class BlueEmptyPlatform extends Tile implements Changable{

	public BlueEmptyPlatform() {
		super("blueemptyplatform", "Blue Empty Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new BluePlatform();
	}

}
