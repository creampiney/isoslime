package entity.solid;

import entity.base.SolidEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SlimeFriend extends SolidEntity{

	public SlimeFriend(int posRow, int posCol) {
		super(posRow, posCol, 100, 100);
		setImg();
	}

	@Override
	public void draw(GraphicsContext gc) {
		
		gc.drawImage(getCurrentSprite(), 590-50*this.getPosRow()+50*this.getPosCol(), 50+25*this.getPosRow()+25*this.getPosCol(), 100, 100);
	}

	@Override
	public void setImg() {
		setCurrentSprite(new Image(ClassLoader.getSystemResource("img/entities/slimefriend.png").toString()));
		
	}
	
	
	
	
	
}
