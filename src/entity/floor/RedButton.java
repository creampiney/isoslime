package entity.floor;

import entity.base.Actable;
import entity.base.Destroyable;
import entity.base.Entity;
import entity.base.FloorEntity;
import entity.base.SolidEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;
import tile.base.Tile;
import tile.platform.RedEmptyPlatform;
import tile.platform.RedPlatform;

public class RedButton extends FloorEntity implements Actable {

	private boolean isActive;
	
	public RedButton(int posRow, int posCol) {
		super(posRow, posCol, 100, 120);
		isActive = false;
		setImg();
	}

	public void changeSprite() {
		if (isActive) {
			setCurrentSprite(new Image(ClassLoader.getSystemResource("img/entities/redbuttonactivate.png").toString()));
		}
		else {
			setCurrentSprite(new Image(ClassLoader.getSystemResource("img/entities/redbutton.png").toString()));
		}
		
	}

	public void setImg() {
		setCurrentSprite(new Image(ClassLoader.getSystemResource("img/entities/redbutton.png").toString()));
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(getCurrentSprite(), 590-50*this.getPosRow()+50*this.getPosCol(), 30+25*this.getPosRow()+25*this.getPosCol(), 100, 120);
	}
	
	public void update() {
		Entity upperEntity = GameLogic.getMap().getEntities()[getPosRow()][getPosCol()];
		if ((((GameLogic.getPlayer().getPosRow() == getPosRow()) && (GameLogic.getPlayer().getPosCol() == getPosCol())) || (upperEntity instanceof SolidEntity)) && (!isActive)) {
			isActive = true; 
			changeSprite();
			Tile[][] tiles = GameLogic.getMap().getTiles();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (tiles[i][j] instanceof RedPlatform) {
						tiles[i][j] = ((RedPlatform) tiles[i][j]).getChangedTile();
						if (GameLogic.getMap().getEntities()[i][j] instanceof Destroyable) {
							((Destroyable) GameLogic.getMap().getEntities()[i][j]).destroy();
						}
					}
					else if (tiles[i][j] instanceof RedEmptyPlatform) {
						tiles[i][j] = ((RedEmptyPlatform) tiles[i][j]).getChangedTile();
					}
				}
			}
		}
		else if ((((GameLogic.getPlayer().getPosRow() != getPosRow()) || (GameLogic.getPlayer().getPosCol() != getPosCol())) && !(upperEntity instanceof SolidEntity)) && (isActive)) {
			isActive = false; 
			changeSprite();
			Tile[][] tiles = GameLogic.getMap().getTiles();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (tiles[i][j] instanceof RedPlatform) {
						tiles[i][j] = ((RedPlatform) tiles[i][j]).getChangedTile();
						if (GameLogic.getMap().getEntities()[i][j] instanceof Destroyable) {
							((Destroyable) GameLogic.getMap().getEntities()[i][j]).destroy();
						}
					}
					else if (tiles[i][j] instanceof RedEmptyPlatform) {
						tiles[i][j] = ((RedEmptyPlatform) tiles[i][j]).getChangedTile();
					}
				}
			}
		}
	}
}
