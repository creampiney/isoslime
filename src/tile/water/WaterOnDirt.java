package tile.water;

import tile.base.Fillable;
import tile.base.Tile;
import tile.base.WaterTile;
import tile.normal.CrateOnDirt;

public class WaterOnDirt extends WaterTile  {
	
	public WaterOnDirt() {
		super("waterondirt", "Water on Dirt");
	}
	
	public Tile getChangedTile() {
		return new CrateOnDirt();
	}
	
}
