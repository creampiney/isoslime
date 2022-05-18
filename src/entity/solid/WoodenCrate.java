package entity.solid;

import entity.base.Destroyable;
import entity.base.Droppable;
import entity.base.Slidable;
import entity.base.SolidEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;
import tile.Ice;
import tile.base.Fillable;
import tile.base.Tile;
import tile.base.Walkable;

public class WoodenCrate extends SolidEntity implements Slidable, Destroyable, Droppable {
	
	private int movingX, movingY;
	private int movingZ;
	private boolean isPlayerSlip;
	private boolean isAllowSlide;
	
	public WoodenCrate(int posRow, int posCol) {
		super(posRow, posCol, 100, 110);
		this.setImg();
		isAllowSlide = true;
	}
	
	
	
	public void setImg() {
		setCurrentSprite(new Image(ClassLoader.getSystemResource("img/entities/woodencrate.png").toString()));
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(getCurrentSprite(), 590-50*this.getPosRow()+50*this.getPosCol()+movingX, 40+25*this.getPosRow()+25*this.getPosCol()+movingY+movingZ, 100, 110);
	}
	
	public boolean slide(String key) {
		if (!isAllowSlide) {
			return false;
		}
		setPlayerSlip(key);
		return checkMoveValid(key, true);
	}
	
	public void setPlayerSlip(String key) {
		int playerPosRow = GameLogic.getPlayer().getPosRow();
		int playerPosCol = GameLogic.getPlayer().getPosCol();
		if (key.equals("W")) {
			isPlayerSlip = ((GameLogic.getMap().getTiles()[playerPosRow-1][playerPosCol]) instanceof Ice);
		}
		else if (key.equals("A")) {
			isPlayerSlip = ((GameLogic.getMap().getTiles()[playerPosRow][playerPosCol-1]) instanceof Ice);
		}
		else if (key.equals("S")) {
			isPlayerSlip = ((GameLogic.getMap().getTiles()[playerPosRow+1][playerPosCol]) instanceof Ice);
		}
		else if (key.equals("D")) {
			isPlayerSlip = ((GameLogic.getMap().getTiles()[playerPosRow][playerPosCol+1]) instanceof Ice);
		}
		else {
			isPlayerSlip = false;
		}
	}
	
	public boolean checkMoveValid(String key, boolean requireCondition) {
		int newPosRow = getPosRow();
		int newPosCol = getPosCol();
		
		if (key.equals("W")) {
			newPosRow -= 1;
		}
		else if (key.equals("A")) {
			newPosCol -= 1;
		}
		else if (key.equals("S")) {
			newPosRow += 1;
		}
		else if (key.equals("D")) {
			newPosCol += 1;
		}
		System.out.println("Require Condition" + requireCondition);
		if (((!GameLogic.isTilePlaced(newPosRow, newPosCol))||(newPosRow < 0)||(newPosRow > 9)||(newPosCol < 0)||(newPosCol > 9)) && !requireCondition) {
			movingAnimation(newPosRow, newPosCol, key);
			return true;
		}
		
		if (((GameLogic.isEntityPlaced(newPosRow, newPosCol))||(!GameLogic.isTilePlaced(newPosRow, newPosCol)))&&(requireCondition)&&isAllowSlide) {
			System.out.println("cA");
			return false;
		}
		else if ((GameLogic.isEntityPlaced(newPosRow, newPosCol))&&isAllowSlide) {
			System.out.println("cB");
			return false;
		}
		
		else if (!((newPosRow < 0)||(newPosRow > 9)||(newPosCol < 0)||(newPosCol > 9))) {
			System.out.println("cC");
			if ((!(GameLogic.getMap().getTiles()[newPosRow][newPosCol] instanceof Walkable))&&(!(GameLogic.getMap().getTiles()[newPosRow][newPosCol] instanceof Fillable))&&isAllowSlide) {
				System.out.println("cD");
				return false;
			}
		}
		
		movingAnimation(newPosRow, newPosCol, key);
		return true;
		
	}
	
	public void movingAnimation(int newPosRow, int newPosCol, String key) {
		Thread movingThread = null;
		
		if (key.equals("W")) {
			
			movingThread = new Thread(() -> {
				try {
					for (int i = 0; i < 4; i++) {
						movingX += 10;
						movingY -= 5;
						Thread.sleep(30);
					}
					movingX = 0;
					movingY = 0;
					moveEntity(newPosRow, newPosCol, key);
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
					movingX = 0;
					movingY = 0;
					moveEntity(newPosRow, newPosCol, key);
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
					movingX = 0;
					movingY = 0;
					moveEntity(newPosRow, newPosCol, key);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			
		}
		else if (key.equals("D")) {
			
			movingThread = new Thread(() -> {
				try {
					for (int i = 0; i < 4; i++) {
						movingX += 10;
						movingY += 5;
						Thread.sleep(30);
					}
					movingX = 0;
					movingY = 0;
					moveEntity(newPosRow, newPosCol, key);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
	
		}
		
		movingThread.start();
	}
	
	public void moveEntity(int newPosRow, int newPosCol, String key) {
		if (((newPosRow >= 0)&&(newPosRow <= 9)&&(newPosCol >= 0)&&(newPosCol <= 9))) {
			GameLogic.getMap().setEntity(newPosRow, newPosCol, this);
		}
		GameLogic.getMap().setEntity(getPosRow(), getPosCol(), null);
		setPosRow(newPosRow);
		setPosCol(newPosCol);
		
		
		if (GameLogic.getMap().getTiles()[newPosRow][newPosCol] instanceof Fillable) {
			isAllowSlide = false;
			drop();
			return;
		}
		if (!(GameLogic.getMap().getTiles()[newPosRow][newPosCol] instanceof Walkable)) {
			destroy();
			return;
		}
		
		if ((GameLogic.getMap().getTiles()[newPosRow][newPosCol] instanceof Ice)&&(!isPlayerSlip)) {
			checkMoveValid(key, false);
		}
		
	}
	
	public boolean isAllowSlide() {
		return isAllowSlide;
	}
	
	public void destroy() {
		GameLogic.getMap().setEntity(getPosRow(), getPosCol(), null);
	}
	
	public void drop() {
		Thread dropThread = new Thread(() -> {
			try {
				
				movingZ = 0;
				for (int i = 0; i < 4; i++) {
					movingZ += 12;
					Thread.sleep(30);
				}
				destroy();
				Tile originalTile = GameLogic.getMap().getTiles()[getPosRow()][getPosCol()];
				GameLogic.getMap().setTile(getPosRow(), getPosCol(), ((Fillable) originalTile).getChangedTile());
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		dropThread.start();
	}
}
