package screen;
import entity.base.Entity;
import entity.player.Player;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import logic.GameLogic;
import logic.Map;
import logic.ScreenLogic;
import tile.base.Tile;

public class GameScreen extends BaseScreen{
	
	private Map map;
	private Player player;
	private Image bgImg;
	
	public GameScreen(double w, double h, Player player, Map map) {
		super(w, h);
		this.player = player;
		this.map = map;
		this.bgImg = new Image(ClassLoader.getSystemResource("img/bg/"+map.getBgCode()+".png").toString());
		this.addListener();
	}
	
	public void addListener() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), true);
		});

		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
		});

		this.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputUtility.mouseLeftDown();
		});

		this.setOnMouseReleased((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputUtility.mouseLeftRelease();
		});

		this.setOnMouseEntered((MouseEvent event) -> {
			InputUtility.mouseOnScreen = true;
		});

		this.setOnMouseExited((MouseEvent event) -> {
			InputUtility.mouseOnScreen = false;
		});

	}
	
	public void draw() {
		GraphicsContext gc = this.getGraphicsContext2D();
		Tile[][] tiles = map.getTiles();
		Entity[][] floorEntities = map.getFloorEntities();
		Entity[][] entities = map.getEntities();
		
		// Update Player
		player.update(gc);
		
		// Background
		
		gc.drawImage(bgImg, 0, 0);
		
		for(int i = 0; i < 19; i++) {
			for(int j = Math.max(0, i-9); j < Math.min(10, i+1); j++) {
				
				
				// Tiles
				if (tiles[i-j][j] != null) {
					gc.drawImage(tiles[i-j][j].getImg(), 590-50*i+j*100, 100+25*i, 101, 141);
				}
				
				// Hover Tile
				if ((i - j == ScreenLogic.getTileHover().getPosRow()) && (j == ScreenLogic.getTileHover().getPosCol())) {
					if (ScreenLogic.getTileHover().isVisible()) {
						ScreenLogic.getTileHover().draw(gc);
					}
				}
				
				// Floor Entities
				if (floorEntities[i-j][j] != null) {
					floorEntities[i-j][j].draw(gc);
				}
				
				// Isometric Grid
				if (GameLogic.isMagicMode()) {
					ScreenLogic.getIsoGrid().draw(gc);
				}
				
				
				
				
			}
		}
		
		
		for(int i = 0; i < 19; i++) {
			for(int j = Math.max(0, i-9); j < Math.min(10, i+1); j++) {
				
				// Player
				if ((player.getPosRow() == i-j)&&(player.getPosCol() == j)) {
					player.draw(gc);
				}
				
				// Entities
				if (entities[i-j][j] != null) {
					entities[i-j][j].draw(gc);
				}
				
				
			}
		}
		
		/*
		gc.drawImage(bgImg, 0, 0);
		
		for(int i = 0; i < 19; i++) {
			for(int j = Math.max(0, i-9); j < Math.min(10, i+1); j++) {
				
				
				// Tiles
				if (tiles[i-j][j] != null) {
					gc.drawImage(tiles[i-j][j].getImg(), 590-50*i+j*100, 100+25*i, 101, 141);
				}
				
				// Hover Tile
				if ((i - j == ScreenLogic.getTileHover().getPosRow()) && (j == ScreenLogic.getTileHover().getPosCol())) {
					if (ScreenLogic.getTileHover().isVisible()) {
						ScreenLogic.getTileHover().draw(gc);
					}
				}
				
				// Floor Entities
				if (floorEntities[i-j][j] != null) {
					floorEntities[i-j][j].draw(gc);
				}
				
				// Isometric Grid
				if (GameLogic.isMagicMode()) {
					ScreenLogic.getIsoGrid().draw(gc);
				}
				
				// Player
				if ((player.getPosRow() == i-j)&&(player.getPosCol() == j)) {
					player.draw(gc);
				}
				
				// Entities
				if (entities[i-j][j] != null) {
					entities[i-j][j].draw(gc);
				}
				
				
			}
		}
		*/
		
	}
	
}
