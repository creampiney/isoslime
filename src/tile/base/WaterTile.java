package tile.base;

import tile.normal.CrateOnDirt;

public abstract class WaterTile extends Tile implements Fillable{
	
	public WaterTile(String imgName, String name) {
		super(imgName, name);
	}
	
	public abstract Tile getChangedTile();
	
}
