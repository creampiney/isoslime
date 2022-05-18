package tile.water;

import tile.base.Fillable;
import tile.base.Tile;
import tile.base.WaterTile;
import tile.normal.CrateOnStone;

public class WaterOnStone extends WaterTile {
	
	public WaterOnStone() {
		super("wateronstone", "Water on Stone");
	}
	
	public Tile getChangedTile() {
		return new CrateOnStone();
	}
	
}
