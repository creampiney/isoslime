package tile;

import tile.base.Fillable;
import tile.base.Tile;

public class WaterOnDirt extends Tile implements Fillable{
	
	public WaterOnDirt() {
		super("waterondirt", "Water on Dirt");
	}
	
	public Tile getChangedTile() {
		return new CrateOnDirt();
	}
	
}
