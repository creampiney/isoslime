package tile.platform;

import tile.base.Changable;
import tile.base.Tile;
import tile.base.Walkable;

public class RedPlatform extends Tile implements Walkable, Changable{

	public RedPlatform() {
		super("redplatform", "Red Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new RedEmptyPlatform();
	}

}
