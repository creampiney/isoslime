package tile;

import tile.base.Fillable;
import tile.base.Tile;

public class WaterOnDeepStone extends Tile implements Fillable{
	
	public WaterOnDeepStone() {
		super("waterondeepstone", "Water on Deep Stone");
	}
	
	public Tile getChangedTile() {
		return new CrateOnDeepStone();
	}
	
}
