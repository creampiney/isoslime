package tile;

import tile.base.Fillable;
import tile.base.Tile;

public class WaterOnStone extends Tile implements Fillable {
	
	public WaterOnStone() {
		super("wateronstone", "Water on Stone");
	}
	
	public Tile getChangedTile() {
		return new CrateOnStone();
	}
	
}
