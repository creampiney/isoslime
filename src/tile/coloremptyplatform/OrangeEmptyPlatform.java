package tile.coloremptyplatform;

import tile.base.Changable;
import tile.base.ColorEmptyPlatform;
import tile.base.Tile;
import tile.base.Walkable;
import tile.colorplatform.OrangePlatform;

public class OrangeEmptyPlatform extends ColorEmptyPlatform {

	public OrangeEmptyPlatform() {
		super("orangeemptyplatform", "Orange Empty Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new OrangePlatform();
	}

}


