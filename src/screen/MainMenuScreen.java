package screen;

import javafx.scene.image.Image;

public class MainMenuScreen extends BaseScreen {
	
	public MainMenuScreen(double w, double h) {
		super(w, h);
	}
	
	public void draw() {
		this.getGraphicsContext2D().drawImage(new Image(ClassLoader.getSystemResource("img/bg/titleBackground.png").toString()), 0, 0);
	}

}
