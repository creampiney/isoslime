package screen;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.AudioPlayer;
import logic.GameLogic;

public class InventoryPane extends GridPane {
	
	private SlotPane slot1;
	private SlotPane slot2;
	private SlotPane slot3;
	
	public InventoryPane() {
		initUI();
	}
	
	public void initUI() {
		this.setMinHeight(300);
		this.setMaxHeight(300);
		this.setMinWidth(100);
		this.setMaxWidth(100);
		this.setHgap(20);
		this.setVgap(20);
		
		this.setStyle("-fx-background-color: #c8c8c8;"
				+ "-fx-background-radius: 10px;"
				+ "-fx-border-style: solid solid solid solid;"
				+ "-fx-border-radius: 10px;");
		
		slot1 = new SlotPane(1);
		slot1.setOnMouseClicked(e -> {
			clickSlot(1);
		});
		this.add(slot1, 0, 0);
		
		slot2 = new SlotPane(2);
		slot2.setOnMouseClicked(e -> {
			clickSlot(2);
		});
		this.add(slot2, 0, 1);
		
		slot3 = new SlotPane(3);
		slot3.setOnMouseClicked(e -> {
			clickSlot(3);
		});
		this.add(slot3, 0, 2);
		
		updateAmountLabel();
		
		
	}
	

	
	public void clickSlot(int slotNum) {
		if (slotNum == GameLogic.getSlotSelecting()) {
			AudioPlayer.clickAudio.play();
			inactiveSlot();
		}
		else {
			inactiveSlot();
			activeSlot(slotNum);
		}
	}
	
	public void activeSlot(int slotNum) {
		if (((slotNum == 1)&&(GameLogic.getMagicWandAmount() == 0))||((slotNum == 2)&&(GameLogic.getAxeAmount() == 0))||((slotNum == 3)&&(GameLogic.getWoodAmount() == 0))) {
			return;
		}
		AudioPlayer.clickAudio.play();
		GameLogic.setSlotSelecting(slotNum);
		
		StackPane slot = null;
		if (slotNum == 1) {
			slot = slot1;
		}
		else if (slotNum == 2) {
			slot = slot2;
		}
		else {
			slot = slot3;
		}
		
		slot.setBorder(new Border(new BorderStroke(Color.web("#9e4c36",1.0), 
	            BorderStrokeStyle.SOLID, new CornerRadii(7), new BorderWidths(5))));
	}
	
	public void inactiveSlot() {
		GameLogic.setSlotSelecting(0);
		
		slot1.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
		
		slot2.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
		
		slot3.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
	}
	
	public void updateAmountLabel() {
		slot1.setAmountLabel(GameLogic.getMagicWandAmount());
		slot2.setAmountLabel(GameLogic.getAxeAmount());
		slot3.setAmountLabel(GameLogic.getWoodAmount());
	}
	
	
	
}
