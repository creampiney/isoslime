package entity.player;
import entity.base.Entity;
import entity.base.SolidEntity;
import entity.floor.LevelGate;
import entity.solid.Tree;
import input.InputUtility;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import logic.AudioPlayer;
import logic.GameLogic;
import logic.ScreenLogic;
import screen.GamePane;
import tile.base.Walkable;

public class Player extends Entity {
	
	private int posRow, posCol;
	private int movingX, movingY;
	
	private WritableImage currentSprite;
	private int spriteNumX, spriteNumY;
	
	public Player(int posRow, int posCol) {
		super(posRow, posCol, 100, 100);
		this.setPosRow(posRow);
		this.setPosCol(posCol);
		spriteNumX = 0;
		spriteNumY = 0;
		movingX = 0;
		movingY = 0;
		this.updateSprite();
		GameLogic.setPlayerMoving(false);
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(currentSprite, 590-50*posRow+50*posCol+movingX, 50+25*posRow+25*posCol+movingY, 100, 100);
	}
	
	public void setImg() {
		updateSprite();
	}
	
	public void update(GraphicsContext gc) {
		if (!GameLogic.isPlayerMoving()&&GameLogic.isGameRunning()) {
			if (InputUtility.getKeyTriggered(KeyCode.W)) {
				System.out.println("SHIT");
				checkMoving("W", true);
			}
			if (InputUtility.getKeyTriggered(KeyCode.A)) {
				checkMoving("A", true);
			}
			if (InputUtility.getKeyTriggered(KeyCode.S)) {
				checkMoving("S", true);
			}
			if (InputUtility.getKeyTriggered(KeyCode.D)) {
				checkMoving("D", true);
			}
			
			if (InputUtility.getKeyTriggered(KeyCode.R)) {
				GameLogic.restartLevel();
			}
			
			
		}
		
		if (GameLogic.isGameRunning()) {
			if (InputUtility.isLeftClickTriggered()) {
				if ((ScreenLogic.getTileHover().isVisible())&&(!GameLogic.isPlayerMoving())) {
					
					int hoverPosRow = ScreenLogic.getTileHover().getPosRow();
					int hoverPosCol = ScreenLogic.getTileHover().getPosCol();
					
					if (GameLogic.getSlotSelecting() == 1) {
						if (!(GameLogic.getMap().getFloorEntities()[hoverPosRow][hoverPosCol] instanceof LevelGate) && !(GameLogic.getMap().getEntities()[hoverPosRow][hoverPosCol] instanceof SolidEntity) && (GameLogic.getMap().getTiles()[hoverPosRow][hoverPosCol] instanceof Walkable) && (Math.abs(hoverPosRow - posRow) <= 2) && (Math.abs(hoverPosCol - posCol) <= 2) && (GameLogic.getMagicWandAmount() != 0)) {
							GameLogic.useMagicWand(hoverPosRow, hoverPosCol);
						}
					}
					
					else if (GameLogic.getSlotSelecting() == 2) {
						if ((GameLogic.getMap().getEntities()[hoverPosRow][hoverPosCol] instanceof Tree)&&(GameLogic.getAxeAmount() != 0)) {
							GameLogic.useAxe(hoverPosRow, hoverPosCol);
						}
					}
					
					else if (GameLogic.getSlotSelecting() == 3) {
						if (!(GameLogic.isTilePlaced(hoverPosRow, hoverPosCol))&&(GameLogic.getWoodAmount() != 0)) {
							GameLogic.useWood(hoverPosRow, hoverPosCol);
						}
					}
				}
				
			}
			if (InputUtility.getKeyTriggered(KeyCode.DIGIT1)) {
				ScreenLogic.getInventoryPane().clickSlot(1);
			}
			if (InputUtility.getKeyTriggered(KeyCode.DIGIT2)) {
				ScreenLogic.getInventoryPane().clickSlot(2);
			}
			if (InputUtility.getKeyTriggered(KeyCode.DIGIT3)) {
				ScreenLogic.getInventoryPane().clickSlot(3);
			}
		}
		if (InputUtility.getKeyTriggered(KeyCode.ESCAPE)) {
			if (!GameLogic.isPlayerMoving() && GameLogic.isGameRunning() && !GameLogic.isMenuOpen()) {
				GameLogic.setGameRunning(false);
				((GamePane) ScreenLogic.getCurrentPane()).showMenu();
			}
			else if (!GameLogic.isGameRunning() && GameLogic.isMenuOpen()) {
				GameLogic.setGameRunning(true);
				((GamePane) ScreenLogic.getCurrentPane()).hideMenu();
			}
		}
		
		
		
		this.updateSprite();
	}
	
	public void updateSprite() {
		Image img = new Image(ClassLoader.getSystemResource("img/player/pinkslime.png").toString());
		this.currentSprite = new WritableImage(img.getPixelReader(), (int)(spriteNumX/2)*200, (int)(spriteNumY)*200, 200, 200);
		this.spriteNumX = (spriteNumX + 1)%20;
	}
	
	public void checkMoving(String key, boolean conditionRequire) {
		if (key.equals("W")) {
			if ((posRow != 0)||(!conditionRequire)) {
				if (GameLogic.isMoveValid(posRow-1, posCol, "W", conditionRequire)) {
					movingAnimation("W");
				}
				else if (!conditionRequire) {
					GameLogic.setPlayerMoving(false);
				}
			}
			spriteNumY = 3;
			System.out.println(posRow + " " + posCol);
		}
		else if (key.equals("A")) {
			if ((posCol != 0)||(!conditionRequire)) {
				if (GameLogic.isMoveValid(posRow, posCol-1, "A", conditionRequire)) {
					movingAnimation("A");
				}
				else if (!conditionRequire) {
					GameLogic.setPlayerMoving(false);
				}
			}
			spriteNumY = 2;
			System.out.println(posRow + " " + posCol);
		}
		else if (key.equals("S")) {
			if ((posRow != 9)||(!conditionRequire)) {
				if (GameLogic.isMoveValid(posRow+1, posCol, "S", conditionRequire)) {
					movingAnimation("S");
				}
				else if (!conditionRequire) {
					GameLogic.setPlayerMoving(false);
				}
			}
			spriteNumY = 0;
			System.out.println(posRow + " " + posCol);
		}
		else if (key.equals("D")) {
			if ((posCol != 9)||(!conditionRequire)) {
				if (GameLogic.isMoveValid(posRow, posCol+1, "D", conditionRequire)) {
					movingAnimation("D");
				}
				else if (!conditionRequire) {
					GameLogic.setPlayerMoving(false);
				}
			}
			spriteNumY = 1;
			System.out.println(posRow + " " + posCol);
		}
	}
	
	public void movingAnimation(String key) {
		GameLogic.setPlayerMoving(true);
		Thread movingThread = null;
		AudioPlayer.movingAudio.play();
		if (key.equals("W")) {
			
			movingThread = new Thread(() -> {
				try {
					for (int i = 0; i < 4; i++) {
						movingX += 10;
						movingY -= 5;
						Thread.sleep(30);
					}
					posRow -= 1;
					movingX = 0;
					movingY = 0;
					Thread.sleep(5);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							GameLogic.checkPlayerStat("W");
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			
		}
		else if (key.equals("A")) {
			
			movingThread = new Thread(() -> {
				try {
					for (int i = 0; i < 4; i++) {
						movingX -= 10;
						movingY -= 5;
						Thread.sleep(30);
					}
					posCol -= 1;
					movingX = 0;
					movingY = 0;
					Thread.sleep(5);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							GameLogic.checkPlayerStat("A");
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			
		}
		else if (key.equals("S")) {
			
			movingThread = new Thread(() -> {
				try {
					for (int i = 0; i < 4; i++) {
						movingX -= 10;
						movingY += 5;
						Thread.sleep(30);
					}
					posRow += 1;
					movingX = 0;
					movingY = 0;
					Thread.sleep(5);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							GameLogic.checkPlayerStat("S");
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			
			/*
			movingThread = new Thread(() -> {
				try {
					posRow += 1;
					movingX = 50;
					movingY = -25;
					for (int i = 0; i < 4; i++) {
						movingX -= 10;
						movingY += 5;
						Thread.sleep(30);
					}
					Thread.sleep(5);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							GameLogic.checkPlayerStat("S");
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			*/
			
		}
		else if (key.equals("D")) {
			
			movingThread = new Thread(() -> {
				try {
					/*
					posCol += 1;
					movingX = -50;
					movingY = -25;
					*/
					for (int i = 0; i < 4; i++) {
						movingX += 10;
						movingY += 5;
						Thread.sleep(30);
					}
					posCol += 1;
					movingX = 0;
					movingY = 0;
					Thread.sleep(5);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							GameLogic.checkPlayerStat("D");
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
	
		}
		
		movingThread.start();
	}
	

	public int getPosRow() {
		return posRow;
	}

	public int getPosCol() {
		return posCol;
	}

	public void setPosRow(int posRow) {
		this.posRow = posRow;
	}

	public void setPosCol(int posCol) {
		this.posCol = posCol;
	}
}
