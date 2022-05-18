package entity.floor;

import entity.base.FloorEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;

public class TileHover extends FloorEntity{
	
	private boolean isVisible;
	
	public TileHover(int posRow, int posCol) {
		super(posRow, posCol, 100, 140);
		this.setImg();
		isVisible = false;
	}
	
	public void setPos(int posRow, int posCol) {
		if ((posRow < 0)||(posRow > 9)||(posCol < 0)||(posCol > 9)) {
			isVisible = false;
		}
		else if (GameLogic.isMagicMode()) {
			this.setPosRow(posRow);
			this.setPosCol(posCol);
			isVisible = true;
		}
		else if (GameLogic.getMap().isTilePlaced(posRow, posCol)){
			this.setPosRow(posRow);
			this.setPosCol(posCol);
			isVisible = true;
		}
		else {
			isVisible = false;
		}
	}
	
	public void setImg() {
		setCurrentSprite(new Image(ClassLoader.getSystemResource("img/tiles/hovertile.png").toString()));
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(getCurrentSprite(), 590-50*this.getPosRow()+50*this.getPosCol(), 100+25*this.getPosRow()+25*this.getPosCol(), 101, 141);
	}

	public boolean isVisible() {
		return isVisible;
	}
	
	
}
