package tile.platform;

import tile.base.Changable;
import tile.base.Tile;
import tile.base.Walkable;

public class PurpleEmptyPlatform extends Tile implements Changable{

	public PurpleEmptyPlatform() {
		super("purpleemptyplatform", "Purple Empty Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new PurplePlatform();
	}

}
