package entity.item;

import entity.base.Item;
import javafx.scene.image.Image;
import logic.GameLogic;

public class Wood extends Item {

	public Wood(int posRow, int posCol) {
		super(posRow, posCol);
	}

	@Override
	public void changeSprite() {
		GameLogic.setWoodAmount(GameLogic.getWoodAmount() + 1);
		destroy();
		
	}

	@Override
	public void setImg() {
		setCurrentSprite(new Image(ClassLoader.getSystemResource("img/items/wood.png").toString()));
	}

	
	
}
