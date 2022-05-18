package tile.platform;

import tile.base.Changable;
import tile.base.Tile;
import tile.base.Walkable;

public class GreenPlatform extends Tile implements Walkable, Changable{

	public GreenPlatform() {
		super("greenplatform", "Green Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new GreenEmptyPlatform();
	}

}
