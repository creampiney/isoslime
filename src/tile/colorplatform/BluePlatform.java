package tile.colorplatform;

import tile.base.ColorPlatform;
import tile.base.Tile;
import tile.coloremptyplatform.BlueEmptyPlatform;

public class BluePlatform extends ColorPlatform{

	public BluePlatform() {
		super("blueplatform", "Blue Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new BlueEmptyPlatform();
	}

}
