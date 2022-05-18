package tile.platform;

import tile.base.Changable;
import tile.base.Tile;
import tile.base.Walkable;

public class GreenEmptyPlatform extends Tile implements Changable{

	public GreenEmptyPlatform() {
		super("greenemptyplatform", "Green Empty Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new GreenPlatform();
	}

}
