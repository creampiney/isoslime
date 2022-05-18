package tile.coloremptyplatform;

import tile.base.Changable;
import tile.base.ColorEmptyPlatform;
import tile.base.Tile;
import tile.colorplatform.RedPlatform;

public class RedEmptyPlatform extends ColorEmptyPlatform {

	public RedEmptyPlatform() {
		super("redemptyplatform", "Red Empty Platform");
	}

	public Tile getChangedTile() {
		return new RedPlatform();
	}

}
