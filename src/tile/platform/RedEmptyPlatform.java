package tile.platform;

import tile.base.Changable;
import tile.base.Tile;

public class RedEmptyPlatform extends Tile implements Changable{

	public RedEmptyPlatform() {
		super("redemptyplatform", "Red Empty Platform");
	}

	public Tile getChangedTile() {
		return new RedPlatform();
	}

}
