package entity.item;

import entity.base.Actable;
import entity.base.Destroyable;
import entity.base.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;

public class MagicWand extends Item {

	public MagicWand(int posRow, int posCol) {
		super(posRow, posCol);
	}

	@Override
	public void changeSprite() {
		GameLogic.setMagicWandAmount(GameLogic.getMagicWandAmount() + 1);
		destroy();
		
	}

	@Override
	public void setImg() {
		setCurrentSprite(new Image(ClassLoader.getSystemResource("img/items/magicwand.png").toString()));
	}

	
	
}
