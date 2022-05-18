package entity.floor;

import entity.base.FloorEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class LevelGate extends FloorEntity {
	
	
	public LevelGate(int posRow, int posCol) {
		super(posRow, posCol, 100, 100);
		this.setImg();
	}
	
	public void setImg() {
		setCurrentSprite(new Image(ClassLoader.getSystemResource("img/entities/levelgate.png").toString()));
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(getCurrentSprite(), 590-50*this.getPosRow()+50*this.getPosCol(), 50+25*this.getPosRow()+25*this.getPosCol(), 100, 100);
	}

}
