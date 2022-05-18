package tile.base;
import javafx.scene.image.Image;

public abstract class Tile {
	
	private String name;
	private Image img;
	
	public Tile(String imgName, String name) {
		this.img = new Image(ClassLoader.getSystemResource("img/tiles/"+imgName+".png").toString());
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Image getImg() {
		return img;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	
	
}
