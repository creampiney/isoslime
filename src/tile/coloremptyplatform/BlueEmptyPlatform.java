package tile.coloremptyplatform;

import tile.base.ColorEmptyPlatform;
import tile.base.Tile;
import tile.colorplatform.BluePlatform;

public class BlueEmptyPlatform extends ColorEmptyPlatform {

	public BlueEmptyPlatform() {
		super("blueemptyplatform", "Blue Empty Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new BluePlatform();
	}

}
