package screen;

import javafx.scene.canvas.Canvas;

public abstract class BaseScreen extends Canvas {
	
	public BaseScreen(double w, double h) {
		super(w, h);
	}
	
	public abstract void draw();

	
	
	
}
