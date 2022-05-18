package tile.colorplatform;

import tile.base.Changable;
import tile.base.ColorPlatform;
import tile.base.Tile;
import tile.base.Walkable;
import tile.coloremptyplatform.RedEmptyPlatform;

public class RedPlatform extends ColorPlatform{

	public RedPlatform() {
		super("redplatform", "Red Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new RedEmptyPlatform();
	}

}
