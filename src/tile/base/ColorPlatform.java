package tile.base;

public abstract class ColorPlatform extends Tile implements Walkable, Changable {
	
	public ColorPlatform(String imgName, String name) {
		super(imgName, name);
	}
	
	public abstract Tile getChangedTile();
}
