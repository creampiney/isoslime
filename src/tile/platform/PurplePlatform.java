package tile.platform;

import tile.base.Changable;
import tile.base.Tile;
import tile.base.Walkable;

public class PurplePlatform extends Tile implements Walkable, Changable{

	public PurplePlatform() {
		super("purpleplatform", "Purple Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new PurpleEmptyPlatform();
	}

}