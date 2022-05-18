package entity.solid;

import entity.base.SolidEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CoconutTree extends Tree{
	
	public CoconutTree(int posRow, int posCol) {
		super(posRow, posCol);
		this.setImg();
	}

	public void setImg() {
		setCurrentSprite(new Image(ClassLoader.getSystemResource("img/entities/coconuttree.png").toString()));
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(getCurrentSprite(), 590-50*this.getPosRow()+50*this.getPosCol(), 30+25*this.getPosRow()+25*this.getPosCol(), 100, 120);
	}
	
}
