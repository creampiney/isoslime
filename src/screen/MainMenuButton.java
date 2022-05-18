package screen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class MainMenuButton extends Button {
	
	private static final Font font = Font.loadFont(ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString(), 35);
	
	public MainMenuButton(String label) {
		super(label);
		this.setPrefWidth(300);
		this.setPrefHeight(50);
		this.setPadding(new Insets(2, 0, 2, 0));
		this.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5);");
		this.setFont(font);
		this.setButtonHover();
	}
	
	private void setButtonHover() {
		this.setOnMouseEntered(e -> {
			this.setStyle("-fx-background-color: rgba(0, 100, 100, 1);");
		});
		
		this.setOnMouseExited(e -> {
			this.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5);");
		});
	}

}
