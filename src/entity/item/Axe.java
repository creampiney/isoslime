package entity.item;

import entity.base.Item;
import javafx.scene.image.Image;
import logic.GameLogic;

public class Axe extends Item {

	public Axe(int posRow, int posCol) {
		super(posRow, posCol);
	}

	@Override
	public void changeSprite() {
		GameLogic.setAxeAmount(GameLogic.getAxeAmount() + 1);
		destroy();
		
	}

	@Override
	public void setImg() {
		setCurrentSprite(new Image(ClassLoader.getSystemResource("img/items/axe.png").toString()));
	}

	
	
}
