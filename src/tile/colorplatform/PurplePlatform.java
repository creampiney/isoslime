package tile.colorplatform;

import tile.base.Changable;
import tile.base.ColorPlatform;
import tile.base.Tile;
import tile.base.Walkable;
import tile.coloremptyplatform.PurpleEmptyPlatform;

public class PurplePlatform extends ColorPlatform{

	public PurplePlatform() {
		super("purpleplatform", "Purple Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new PurpleEmptyPlatform();
	}

}