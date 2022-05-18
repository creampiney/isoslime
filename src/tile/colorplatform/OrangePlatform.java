package tile.colorplatform;

import tile.base.Changable;
import tile.base.ColorPlatform;
import tile.base.Tile;
import tile.base.Walkable;
import tile.coloremptyplatform.OrangeEmptyPlatform;

public class OrangePlatform extends ColorPlatform{

	public OrangePlatform() {
		super("orangeplatform", "Orange Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new OrangeEmptyPlatform();
	}

}
