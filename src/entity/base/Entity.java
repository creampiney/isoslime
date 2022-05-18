package entity.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
	
	private int posRow, posCol;
	private double width, height;
	private Image currentSprite;

	public Entity(int posRow, int posCol, double width, double height) {
		this.setPosRow(posRow);
		this.setPosCol(posCol);
		this.width = width;
		this.height = height;
	}
	
	public abstract void draw(GraphicsContext gc);
	
	public abstract void setImg();

	public int getPosRow() {
		return posRow;
	}

	public int getPosCol() {
		return posCol;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
	
	public Image getCurrentSprite() {
		return currentSprite;
	}

	public void setPosRow(int posRow) {
		this.posRow = posRow;
	}

	public void setPosCol(int posCol) {
		this.posCol = posCol;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setCurrentSprite(Image img) {
		this.currentSprite = img;
	}

	
	
	
	
	
	
}
