package tile.coloremptyplatform;

import tile.base.Changable;
import tile.base.ColorEmptyPlatform;
import tile.base.Tile;
import tile.base.Walkable;
import tile.colorplatform.GreenPlatform;

public class GreenEmptyPlatform extends ColorEmptyPlatform {

	public GreenEmptyPlatform() {
		super("greenemptyplatform", "Green Empty Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new GreenPlatform();
	}

}
