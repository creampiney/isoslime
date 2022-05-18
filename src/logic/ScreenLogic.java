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
		
		if ((level == 1)&&(!isTutorialShown[0])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(0);	
		}
		else if ((level == 2)&&(!isTutorialShown[1])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(1);	
		}
		else if ((level == 3)&&(!isTutorialShown[2])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(2);	
		}
		else if ((level == 4)&&(!isTutorialShown[3])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(3);	
		}
		else if ((level == 6)&&(!isTutorialShown[4])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(4);	
		}
		else if ((level == 8)&&(!isTutorialShown[5])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(5);	
		}
		else if ((level == 9)&&(!isTutorialShown[6])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(6);	
		}
		else if ((level == 10)&&(!isTutorialShown[7])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(7);	
		}
		else if ((level == 15)&&(!isTutorialShown[8])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(8);	
		}
		else if ((level == 17)&&(!isTutorialShown[9])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(9);	
		}
		else if ((level == 18)&&(!isTutorialShown[10])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(10);	
		}
		else if ((level == 24)&&(!isTutorialShown[11])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(11);	
		}
		else if ((level == 25)&&(!isTutorialShown[12])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(12);	
		}
		else if ((level == 32)&&(!isTutorialShown[13])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(13);	
		}
		else if ((level == 34)&&(!isTutorialShown[14])) {
			tutorialThread = ObjectGenerator.buildTutorialThread(14);	
		}
		
		if (tutorialThread != null) {
			tutorialThread.start();
		}
		
		
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

	public static boolean[] isTutorialShown() {
		return isTutorialShown;
	}

	public static void setTutorialShown(boolean[] isTutorialShown) {
		ScreenLogic.isTutorialShown = isTutorialShown;
	}
	
	
	
}
