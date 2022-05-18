package tile.coloremptyplatform;

import tile.base.Changable;
import tile.base.ColorEmptyPlatform;
import tile.base.Tile;
import tile.base.Walkable;
import tile.colorplatform.PurplePlatform;

public class PurpleEmptyPlatform extends ColorEmptyPlatform {

	public PurpleEmptyPlatform() {
		super("purpleemptyplatform", "Purple Empty Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new PurplePlatform();
	}

}
