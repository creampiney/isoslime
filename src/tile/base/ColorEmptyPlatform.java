package tile.base;

public abstract class ColorEmptyPlatform extends Tile implements Changable {
	
	public ColorEmptyPlatform(String imgName, String name) {
		super(imgName, name);
	}
	
	public abstract Tile getChangedTile();
}
