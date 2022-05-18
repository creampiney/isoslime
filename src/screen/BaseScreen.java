package screen;

import javafx.scene.canvas.Canvas;

public abstract class BaseScreen extends Canvas {
	
	private boolean isRequireRender;
	
	public BaseScreen(double w, double h) {
		super(w, h);
	}
	
	public abstract void draw();

	public boolean isRequireRender() {
		return isRequireRender;
	}

	public void setRequireRender(boolean isRequireRender) {
		this.isRequireRender = isRequireRender;
	}

	
	
	
}
