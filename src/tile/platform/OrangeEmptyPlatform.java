package tile.platform;

import tile.base.Changable;
import tile.base.Tile;
import tile.base.Walkable;

public class OrangeEmptyPlatform extends Tile implements Changable{

	public OrangeEmptyPlatform() {
		super("orangeemptyplatform", "Orange Empty Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new OrangePlatform();
	}

}


