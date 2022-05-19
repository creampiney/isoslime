package entity.floor;

import entity.base.Actable;
import entity.base.FloorEntity;
import javafx.scene.canvas.GraphicsContext;

public abstract class FloorButton extends FloorEntity implements Actable {
	
	protected boolean isActive;
	
	public FloorButton(int posRow, int posCol) {
		super(posRow, posCol, 100, 120);
		isActive = false;
		setImg();
	}
	
	public abstract void changeSprite();
	
	public abstract void setImg();
	
	public abstract void draw(GraphicsContext gc);
	
	public abstract void update();
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
