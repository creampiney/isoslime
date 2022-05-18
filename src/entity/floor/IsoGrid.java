package entity.floor;

import entity.base.FloorEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class IsoGrid extends FloorEntity {
	
	public IsoGrid() {
		super(0, 0, 1000, 500);
		this.setImg();
	}

	public void setImg() {
		setCurrentSprite(new Image(ClassLoader.getSystemResource("img/entities/isogrid.png").toString()));
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(getCurrentSprite(), 140, 100, 1000, 500);
	}
}
