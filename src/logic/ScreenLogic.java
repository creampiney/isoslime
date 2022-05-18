package logic;

import entity.floor.IsoGrid;
import entity.floor.TileHover;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import screen.BasePane;
import screen.InventoryPane;
import screen.MainMenuPane;
import screen.GamePane;

public class ScreenLogic {
	
	private static Stage stage;
	private static Scene scene;
	private static BasePane root;
	private static TileHover tileHover;
	private static final IsoGrid isoGrid = new IsoGrid();
	private static InventoryPane inventoryPane;
	
	private static boolean[] isTutorialShown = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	
	public static void init(Stage newStage) {
		stage = newStage;
		root = new MainMenuPane();
		scene = new Scene(root, 1280, 800);
		stage.setScene(scene);
		stage.setTitle("Test Final Project");
		stage.getIcons().add(new Image(ClassLoader.getSystemResource("rickroll.png").toString()));
		stage.setResizable(false);
		show();
		AudioPlayer.runMainMenuAudio();
	}
	
	public static void show() {
		stage.show();
	}
	
	public static void changeScene(BasePane root) {
		ScreenLogic.root = root;
		scene = new Scene(root, 1280, 800);
		stage.setScene(scene);
		root.requestFocus();
		tileHover = new TileHover(0, 0);
	}
	
	public static void checkTutorialShow() {
		int level = GameLogic.getLevel();
		Thread tutorialThread = GameLogic.getTutorialThread();
		if (GameLogic.getGameplayMode() != 1) {
			return;
		}
		
		if (level == 1) {		// Tutorial 1 2 3
				if (!isTutorialShown[0]) {
					tutorialThread = new Thread(() -> {

							System.out.println("HAHAHA");
							GameLogic.setGameRunning(false);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									((GamePane) root).showTutorialPane(0);
								}
									
							});
							synchronized (GameLogic.getTutorialThread()){
							       try{
							    	   GameLogic.getTutorialThread().wait();
							       } catch (InterruptedException e) {
							    	   isTutorialShown[0] = true;
										GameLogic.setGameRunning(true);
										System.out.println(GameLogic.isGameRunning());
							       }
					
				
						
							}
					});
					
				}
			}
		
		tutorialThread.start();
		
	}
	
	public static BasePane getCurrentPane() {
		return root;
	}
	
	public static TileHover getTileHover() {
		return tileHover;
	}
	
	public static IsoGrid getIsoGrid() {
		return isoGrid;
	}

	public static InventoryPane getInventoryPane() {
		return inventoryPane;
	}
	
	public static void setInventoryPane(InventoryPane invPane) {
		inventoryPane = invPane;
	}
	
	
}
