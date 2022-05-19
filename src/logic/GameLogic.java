package logic;

import java.util.ArrayList;
import java.util.Random;

import entity.base.Actable;
import entity.base.Entity;
import entity.base.Slidable;
import entity.base.SolidEntity;
import entity.floor.LevelGate;
import entity.player.Player;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import screen.GamePane;
import tile.base.Tile;
import tile.base.Walkable;
import tile.normal.Ice;
import tile.normal.WoodPlatform;

public class GameLogic {
	
	private static Map map;
	private static Player player;
	private static int level;
	private static int score;
	
	private static AnimationTimer animation;
	private static Thread timerThread;
	private static Thread tutorialThread;
	private static CountdownTimer countdownTimer;
	
	private static int slotSelecting;
	
	private static int magicWandAmount;
	private static int axeAmount;
	private static int woodAmount;
	
	private static boolean isGameRunning;
	private static boolean isPlayerMoving;
	private static boolean isMagicMode;
	private static boolean isMenuOpen;
	
	private static int gameplayMode; // 1 = Story, 2 = Puzzle
	
	private static ArrayList<Actable> removingActable;
	
	public static void initNormalGame(int levelInt) {
		isMenuOpen = false;
		isGameRunning = true;
		slotSelecting = 0;
		resetAnimationTimer();
		InputUtility.clearKeyTriggered();
		level = levelInt;
		isMagicMode = false;
		Thread mapThread = new Thread(() -> {
			map = new Map("story/map"+level);
			player = new Player(map.getStartRow(), map.getStartCol());
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					ScreenLogic.changeScene(new GamePane(player, map));
					ScreenLogic.checkTutorialShow();
				}
			});
			
		});
		mapThread.start();
		
		
	}
	
	public static void initTimerGame(int levelInt) {
		isMenuOpen = false;
		isGameRunning = true;
		slotSelecting = 0;
		resetAnimationTimer();
		InputUtility.clearKeyTriggered();
		level = levelInt;
		isMagicMode = false;
		Thread mapThread = new Thread(() -> {
			map = new Map("puzzle/mapp"+level);
			player = new Player(map.getStartRow(), map.getStartCol());
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					ScreenLogic.changeScene(new GamePane(player, map));
					startCountDownTimer();
					
				}
			});
			
		});
		mapThread.start();
		
		
		
	}
	
	
	
	public static void initTimerMode() {
		countdownTimer = new CountdownTimer(1,30,0);
		score = -1;
		nextLevel();
	}
	
	
	
	public static void resetAnimationTimer() {
		isMenuOpen = false;
		if (animation != null) {
			animation.stop();
		}
		if (timerThread != null) {
			timerThread.interrupt();
			timerThread = null;
		}
		if (tutorialThread != null) {
			tutorialThread.interrupt();
		}
		tutorialThread = new Thread(() -> {});
			
	
	}
	
	public static void setAnimationTimer(AnimationTimer newAnimation) {
		animation = newAnimation;
		animation.start();
	}
	
	
	
	public static void startCountDownTimer() {
		
		//countdownTimer = new CountdownTimer(2,0,0);
		timerThread = new Thread(() -> {
			try {
				runCountDownTimer();
			} catch (InterruptedException e1) {
				countdownTimer.setStop(true);
				//e1.printStackTrace();
			}
		});
		timerThread.start();
		
		
	}
	
	public static void runCountDownTimer() throws InterruptedException {
		countdownTimer.setStop(false);
		while(!countdownTimer.isTimerEmpty()) {
			Thread.sleep(50);
			
			Platform.runLater(new Runnable(){
				public void run() {
					if (ScreenLogic.getCurrentPane() instanceof GamePane) {
						((GamePane) ScreenLogic.getCurrentPane()).setStoryLabel(countdownTimer.toString());
					}
				}
			});		
			
			countdownTimer.decrementTimer(5);
		}
		countdownTimer.setStop(true);
		
		if(countdownTimer.isTimerEmpty()) {
			
			Platform.runLater(new Runnable(){
				public void run() {
					timerModeEnd();
				}
			});
			
			
		}
	}
	
	
	
	
	
	
	
	public static boolean isMoveValid(int posRow, int posCol, String key, boolean conditionRequire) {
		if (((posRow < 0)||(posRow > 9)||(posCol < 0)||(posCol > 9))) {
			return !conditionRequire;
		}
		
		if (isEntityPlaced(posRow, posCol)) {
			Entity entity = map.getEntities()[posRow][posCol];
			
			// Slidable Entity
			if (entity instanceof Slidable) {
				if ((((Slidable) entity).isAllowSlide())) {
					if (!(((Slidable) entity).slide(key))) {
						return false;
					}
				}
				else {
					GameLogic.setPlayerMoving(false);
					return false;
				}
			}
			// Solid Entity
			else if (entity instanceof SolidEntity) {
				return false;
			}
			
			
		}
		return map.isMoveValid(posRow, posCol)||(!conditionRequire);
	}
	
	public static boolean isSolidMoveValid(int posRow, int posCol) {
		if (((posRow < 0)||(posRow > 9)||(posCol < 0)||(posCol > 9))) {
			return false;
		}
		return map.isMoveValid(posRow, posCol);
	}
	
	public static boolean isEntityPlaced(int posRow, int posCol) {
		if (((posRow < 0)||(posRow > 9)||(posCol < 0)||(posCol > 9))) {
			return false;
		}
		return map.isEntityPlaced(posRow, posCol);
	}
	
	public static boolean isTilePlaced(int posRow, int posCol) {
		if (((posRow < 0)||(posRow > 9)||(posCol < 0)||(posCol > 9))) {
			return false;
		}
		return map.isTilePlaced(posRow, posCol);
	}
	
	public static void nextLevel() {
		
		if (gameplayMode == 1) {
			level += 1;
			AudioPlayer.levelCompleteAudio.play();
			if (level == 51) {
				storyModeClear();
			}
			else {
				initNormalGame(level);
			}
			
		}
		else if (gameplayMode == 2) {
			countdownTimer.incrementTimer(30);
			score += 1;
			if (score != 0) {
				AudioPlayer.levelCompleteAudio.play();
			}
			Random rand = new Random(System.currentTimeMillis());
			level = 1 + rand.nextInt(49);  // 1 to 50
			initTimerGame(level);
		}
		
	}
	
	public static void restartLevel() {
		if (gameplayMode == 1) {
			initNormalGame(level);
		}
		else {
			initTimerGame(level);
		}
		
	}
	
	public static void storyModeClear() {
		isGameRunning = false;
		if (ScreenLogic.getCurrentPane() instanceof GamePane) {
			((GamePane) ScreenLogic.getCurrentPane()).showStoryModeClear();
		}
		
	}
	
	public static void timerModeEnd() {
		isGameRunning = false;
		AudioPlayer.gameOverAudio.play();
		if (ScreenLogic.getCurrentPane() instanceof GamePane) {
			((GamePane) ScreenLogic.getCurrentPane()).showTimerModeEnd();;
		}
	}
	
	
	public static void checkPlayerStat(String key) {
		
		if (checkPlayerFall()) {
			// Player fall from tile
			AudioPlayer.fallingAudio.play();
			restartLevel();
		}
		else if (checkWin()) {
			// Next Level
			nextLevel();
		}
		else if (checkSlipDone(key)) {
			// Check when slipping is done while pushing
			GameLogic.setPlayerMoving(false);
		}
		else if (checkSlip()) {
			// Slip
			System.out.println("checkSlip");
			player.checkMoving(key, false);
		}
		else {
			GameLogic.setPlayerMoving(false);
		}
		
		
	}
	
	public static boolean checkWin() {
		int posRow = player.getPosRow();
		int posCol = player.getPosCol();
		return ((map.getFloorEntities()[posRow][posCol]) instanceof LevelGate);
	}
	
	public static boolean checkSlip() {
		int posRow = player.getPosRow();
		int posCol = player.getPosCol();
		return ((map.getTiles()[posRow][posCol]) instanceof Ice);
	}
	
	public static boolean checkSlipDone(String key) {
		int posRow = player.getPosRow();
		int posCol = player.getPosCol();
		if (!checkSlip()) {
			return false;
		}
		
		
		Entity firstEntity = null;
		Entity secondEntity = null;
		if (key.equals("W")) {
			if (posRow >= 1) {
				firstEntity = map.getEntities()[posRow-1][posCol];
			}
			if (posRow >= 2) {
				secondEntity = map.getEntities()[posRow-2][posCol];
			}
		}
		else if (key.equals("A")) {
			if (posCol >= 1) {
				firstEntity = map.getEntities()[posRow][posCol-1];
			}
			if (posCol >= 2) {
				secondEntity = map.getEntities()[posRow][posCol-2];
			}
		}
		else if (key.equals("S")) {
			if (posRow <= 8) {
				firstEntity = map.getEntities()[posRow+1][posCol];
			}
			if (posRow <= 7) {
				secondEntity = map.getEntities()[posRow+2][posCol];
			}
		}
		else if (key.equals("D")) {
			if (posCol <= 8) {
				firstEntity = map.getEntities()[posRow][posCol+1];
			}
			if (posCol <= 7) {
				secondEntity = map.getEntities()[posRow][posCol+2];
			}
		}
		
		//	1) Check for solid entity (but not slidable) collide with player
		//	2) Check for slidable entity collides with solid entity
		//	3) Check for slidable entity dropping
		
		if (!(firstEntity instanceof Slidable)&&(firstEntity instanceof SolidEntity)) {
			return true;
		}
		if ((firstEntity instanceof Slidable)&&(secondEntity instanceof SolidEntity)) {
			return true;
		}
		if (firstEntity instanceof Slidable) {
			if (!((Slidable) firstEntity).isAllowSlide()) {
				return true;
			}
		}
		return false;
	
	}
	
	public static boolean checkPlayerFall() {
		int posRow = player.getPosRow();
		int posCol = player.getPosCol();
		if ((posRow < 0)||(posRow > 9)||(posCol < 0)||(posCol > 9)) {
			return true;
		}
		return (!((map.getTiles()[posRow][posCol]) instanceof Walkable));
	}
	
	
	public static void useMagicWand(int posRow, int posCol) {
		magicWandAmount -= 1;
		player.setPosRow(posRow);
		player.setPosCol(posCol);
		ScreenLogic.getInventoryPane().updateAmountLabel();
		if (magicWandAmount == 0) {
			ScreenLogic.getInventoryPane().inactiveSlot();
		}
		AudioPlayer.magicWandAudio.play();
	}
	
	public static void useAxe(int posRow, int posCol) {
		axeAmount -= 1;
		woodAmount += 1;
		map.setEntity(posRow, posCol, null);
		ScreenLogic.getInventoryPane().updateAmountLabel();
		if (axeAmount == 0) {
			ScreenLogic.getInventoryPane().inactiveSlot();
		}
		AudioPlayer.choppingAudio.play();
	}
	
	public static void useWood(int posRow, int posCol) {
		woodAmount -= 1;
		map.setTile(posRow, posCol, new WoodPlatform());
		ScreenLogic.getInventoryPane().updateAmountLabel();
		if (woodAmount == 0) {
			ScreenLogic.getInventoryPane().inactiveSlot();
		}
		AudioPlayer.woodAudio.play();
	}

	public static boolean isPlayerMoving() {
		return isPlayerMoving;
	}

	public static void setPlayerMoving(boolean isPlayerMoving) {
		GameLogic.isPlayerMoving = isPlayerMoving;
	}
	
	public static Map getMap() {
		return map;
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static boolean isMagicMode() {
		return isMagicMode;
	}
	
	public static void setMagicMode(boolean magicMode) {
		isMagicMode = magicMode;
	}
	
	public static void setSlotSelecting(int slot) {
		slotSelecting = slot;
	}
	
	public static int getSlotSelecting() {
		return slotSelecting;
	}

	public static int getMagicWandAmount() {
		return magicWandAmount;
	}

	public static int getAxeAmount() {
		return axeAmount;
	}

	public static int getWoodAmount() {
		return woodAmount;
	}

	public static void setMagicWandAmount(int magicWandAmount) {
		GameLogic.magicWandAmount = magicWandAmount;
	}

	public static void setAxeAmount(int axeAmount) {
		GameLogic.axeAmount = axeAmount;
	}

	public static void setWoodAmount(int woodAmount) {
		GameLogic.woodAmount = woodAmount;
	}

	public static ArrayList<Actable> getRemovingActable() {
		return removingActable;
	}

	public static void setRemovingActable(ArrayList<Actable> removingActable) {
		GameLogic.removingActable = removingActable;
	}
	
	public static int getLevel() {
		return level;
	}
	
	public static int getGameplayMode() {
		return gameplayMode;
	}
	
	public static void setGameplayMode(int mode) {
		gameplayMode = mode;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		GameLogic.score = score;
	}

	public static boolean isGameRunning() {
		return isGameRunning;
	}

	public static void setGameRunning(boolean isGameRunning) {
		GameLogic.isGameRunning = isGameRunning;
	}

	public static Thread getTimerThread() {
		return timerThread;
	}

	public static boolean isMenuOpen() {
		return isMenuOpen;
	}

	public static void setMenuOpen(boolean isMenuOpen) {
		GameLogic.isMenuOpen = isMenuOpen;
	}

	public static Thread getTutorialThread() {
		return tutorialThread;
	}

	public static void setTutorialThread(Thread tutorialThread) {
		GameLogic.tutorialThread = tutorialThread;
	}
	
	
	
	
	
	
}
