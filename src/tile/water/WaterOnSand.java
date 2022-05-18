package tile.water;

import tile.base.Fillable;
import tile.base.Tile;
import tile.base.WaterTile;
import tile.normal.CrateOnSand;

public class WaterOnSand extends WaterTile {
	
	public WaterOnSand() {
		super("wateronsand", "Water on Sand");
	}
	
	public Tile getChangedTile() {
		return new CrateOnSand();	
	}
	
}

