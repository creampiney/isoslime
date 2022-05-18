package tile;

import tile.base.Fillable;
import tile.base.Tile;

public class WaterOnSand extends Tile implements Fillable{
	
	public WaterOnSand() {
		super("wateronsand", "Water on Sand");
	}
	
	public Tile getChangedTile() {
		return new CrateOnSand();	
	}
	
}

