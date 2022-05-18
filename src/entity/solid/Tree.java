package entity.solid;

import entity.base.SolidEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tree extends SolidEntity{
	
	public Tree(int posRow, int posCol) {
		super(posRow, posCol, 100, 120);
		this.setImg();
	}

	public void setImg() {
		setCurrentSprite(new Image(ClassLoader.getSystemResource("img/entities/tree.png").toString()));
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(getCurrentSprite(), 590-50*this.getPosRow()+50*this.getPosCol(), 30+25*this.getPosRow()+25*this.getPosCol(), 100, 120);
	}
	
}
