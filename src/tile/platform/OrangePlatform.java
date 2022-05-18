package tile.platform;

import tile.base.Changable;
import tile.base.Tile;
import tile.base.Walkable;

public class OrangePlatform extends Tile implements Walkable, Changable{

	public OrangePlatform() {
		super("orangeplatform", "Orange Platform");
	}

	@Override
	public Tile getChangedTile() {
		return new OrangeEmptyPlatform();
	}

}
