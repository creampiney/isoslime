package screen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SlotPane extends StackPane {
	
	private int slotNum;
	private Label amountLabel;
	
	public SlotPane(int slot) {
		slotNum = slot;
		initUI();
	}
	
	public void initUI() {
		this.setMinHeight(80);
		this.setMaxHeight(80);
		this.setMinWidth(80);
		this.setMaxWidth(80);
		this.setStyle("-fx-background-color: #a0a0a0;"
				+ "-fx-background-radius: 10px;"
				+ "-fx-border-style: solid solid solid solid;"
				+ "-fx-border-radius: 10px;");
		
		Image image = null;
		amountLabel = new Label("30");
		amountLabel.setFont(Font.loadFont(ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString(), 17));
		amountLabel.setAlignment(Pos.BOTTOM_RIGHT);
		amountLabel.setPadding(new Insets(5));
		
		// Amount Label and Img
		if (slotNum == 1) {
			image = new Image(ClassLoader.getSystemResource("img/items/magicwand.png").toString());
		}
		else if (slotNum == 2) {
			image = new Image(ClassLoader.getSystemResource("img/items/axe.png").toString());
		}
		else {
			image = new Image(ClassLoader.getSystemResource("img/items/wood.png").toString());
		}
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(80);
		imageView.setFitWidth(80);
		this.getChildren().add(imageView);
		this.getChildren().add(amountLabel);
		amountLabel.setAlignment(Pos.BOTTOM_RIGHT);
		this.setAlignment(amountLabel, Pos.BOTTOM_RIGHT);
		
		
		
		
		
		this.setOnMouseEntered(e -> {
			this.setBackground(new Background(new BackgroundFill(Color.web("#c8c8c8",1.0), new CornerRadii(10), Insets.EMPTY)));
		});
		
		this.setOnMouseExited(e -> {
			this.setBackground(new Background(new BackgroundFill(Color.web("#a0a0a0",1.0), new CornerRadii(10), Insets.EMPTY)));
		});
		
		this.setOnMouseClicked(e -> {
			
		});
	}
	
	public void setAmountLabel(int num) {
		amountLabel.setText(Integer.toString(num));
	}
}
