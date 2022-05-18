package tile.water;

import tile.base.Tile;
import tile.base.WaterTile;
import tile.normal.CrateOnDeepStone;

public class WaterOnDeepStone extends WaterTile {
	
	public WaterOnDeepStone() {
		super("waterondeepstone", "Water on Deep Stone");
	}
	
	public Tile getChangedTile() {
		return new CrateOnDeepStone();
	}
	
}
