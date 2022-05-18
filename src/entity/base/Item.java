package entity.base;

import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import logic.ScreenLogic;

public abstract class Item extends Entity implements Actable, Destroyable{
	
	public Item(int posRow, int posCol) {
		super(posRow, posCol, 100, 100);
		setImg();
	}
	
	public void destroy() {
		GameLogic.getMap().setFloorEntity(getPosRow(), getPosCol(), null);
		
	}

	public abstract void changeSprite();

	@Override
	public void update() {
		if ((GameLogic.getPlayer().getPosRow() == getPosRow()) && (GameLogic.getPlayer().getPosCol() == getPosCol())) {
			changeSprite();
			GameLogic.getRemovingActable().add(this);
			ScreenLogic.getInventoryPane().updateAmountLabel();
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(getCurrentSprite(), 600-50*this.getPosRow()+50*this.getPosCol(), 70+25*this.getPosRow()+25*this.getPosCol(), 80, 80);
	}

	@Override
	public abstract void setImg();

}
