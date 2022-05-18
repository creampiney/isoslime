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
import tile.coloremptyplatform.BlueEmptyPlatform;
import tile.colorplatform.BluePlatform;

public class BlueButton extends FloorEntity implements Actable {

	private boolean isActive;
	
	public BlueButton(int posRow, int posCol) {
		super(posRow, posCol, 100, 120);
		isActive = false;
		setImg();
	}

	public void changeSprite() {
		if (isActive) {
			setCurrentSprite(new Image(ClassLoader.getSystemResource("img/entities/bluebuttonactivate.png").toString()));
		}
		else {
			setCurrentSprite(new Image(ClassLoader.getSystemResource("img/entities/bluebutton.png").toString()));
		}
		
	}

	public void setImg() {
		setCurrentSprite(new Image(ClassLoader.getSystemResource("img/entities/bluebutton.png").toString()));
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
					if (tiles[i][j] instanceof BluePlatform) {
						tiles[i][j] = ((BluePlatform) tiles[i][j]).getChangedTile();
						if (GameLogic.getMap().getEntities()[i][j] instanceof Destroyable) {
							((Destroyable) GameLogic.getMap().getEntities()[i][j]).destroy();
						}
					}
					else if (tiles[i][j] instanceof BlueEmptyPlatform) {
						tiles[i][j] = ((BlueEmptyPlatform) tiles[i][j]).getChangedTile();
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
					if (tiles[i][j] instanceof BluePlatform) {
						tiles[i][j] = ((BluePlatform) tiles[i][j]).getChangedTile();
						if (GameLogic.getMap().getEntities()[i][j] instanceof Destroyable) {
							((Destroyable) GameLogic.getMap().getEntities()[i][j]).destroy();
						}
					}
					else if (tiles[i][j] instanceof BlueEmptyPlatform) {
						tiles[i][j] = ((BlueEmptyPlatform) tiles[i][j]).getChangedTile();
					}
				}
			}
		}
	}
}
