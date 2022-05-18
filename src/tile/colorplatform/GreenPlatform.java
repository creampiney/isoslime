package tile.colorplatform;

import tile.base.Changable;
import tile.base.ColorPlatform;
import tile.base.Tile;
import tile.base.Walkable;
import tile.coloremptyplatform.GreenEmptyPlatform;

public class GreenPlatform extends ColorPlatform{

	public GreenPlatform() {
		super("greenplatform", "Green Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new GreenEmptyPlatform();
	}

}
