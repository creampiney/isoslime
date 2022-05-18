package screen;

import java.util.ArrayList;

import entity.base.Actable;
import entity.player.Player;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.AudioPlayer;
import logic.GameLogic;
import logic.Map;
import logic.ObjectGenerator;
import logic.ScreenLogic;

public class GamePane extends BasePane implements Animatable, HasListener{
	
	private GameScreen canvas;
	private Label storyLabel;
	private Label sceneLabel;
	private StackPane allStackPane;
	private InventoryPane inventoryPane;
	private VBox menuPane;
	
	public GamePane(Player player, Map map) {
		canvas = new GameScreen(1280, 800, player, map);
		this.getChildren().add(canvas);
		canvas.draw();
		GameLogic.setAnimationTimer(getAnimation());
		this.addListener();
		this.initUI();
		this.buildMenu();
		ScreenLogic.setInventoryPane(inventoryPane);
		if (GameLogic.getGameplayMode() == 1) {
			setStoryLabel(ObjectGenerator.buildStoryText(GameLogic.getLevel()));
			setSceneLabel("Scene "+GameLogic.getLevel()+" / 50");
		}
		else if (GameLogic.getGameplayMode() == 2) {
			setStoryLabel("00:00:00");
			setSceneLabel("Score "+GameLogic.getScore());
		}
		
		
	}
	
	public void draw() {
		canvas.draw();
	}
	

	
	public AnimationTimer getAnimation() {
	
			return new AnimationTimer() {
				public void handle(long now) {
					System.out.println("Free Memory (in bytes): " + Runtime.getRuntime().freeMemory());
					canvas.draw();
					InputUtility.updateInputState();
					if (GameLogic.getSlotSelecting() == 3) {
						GameLogic.setMagicMode(true);
					}
					else {
						GameLogic.setMagicMode(false);
					}
					GameLogic.setRemovingActable(new ArrayList<Actable>());
					for (Actable eachActable : GameLogic.getMap().getActableEntity()) {
						eachActable.update();
					}
					GameLogic.getMap().getActableEntity().removeAll(GameLogic.getRemovingActable());
				}
			};
		
	}
	
	public GameScreen getCanvas() {
		return canvas;
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

		this.setOnMouseMoved((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});

		this.setOnMouseDragged((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});
		
		this.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {

	        //x and y mouse positions
	        //System.out.println(e.getX() + " " + e.getY());
	        double isoX = e.getX() - 640;
	        double isoY = e.getY() - 100;
	        double carX = -(0.5*isoX - isoY);
	        double carY = 0.5*isoX + isoY;
	        
	        if ((carX < 0)||(carY < 0)) {
	        	ScreenLogic.getTileHover().setPos(-1, -1);
	        }
	        //System.out.println(carX + " " + carY);
	        else {
	        	ScreenLogic.getTileHover().setPos((int) carX/50, (int) carY/50);
	        }
	    });
	}
	
	public void initUI() {
		
		StackPane stackPane = new StackPane();
		this.getChildren().add(stackPane);
		
		
		// Story Label
		StackPane storyPane = new StackPane();
		stackPane.getChildren().add(storyPane);
		stackPane.setAlignment(storyPane, Pos.BOTTOM_CENTER);
		storyPane.setAlignment(Pos.BOTTOM_CENTER);
		storyLabel = new Label("Test Test");
		storyLabel.setFont(Font.loadFont(ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString(), 30));
		storyLabel.setAlignment(Pos.CENTER);
		storyLabel.setPadding(new Insets(30));
		storyLabel.setMaxWidth(800);
		storyLabel.setMinWidth(800);
		storyPane.getChildren().add(storyLabel);
		
		// Scene Label
		StackPane sceneLabelPane = new StackPane();
		stackPane.getChildren().add(sceneLabelPane);
		stackPane.setAlignment(sceneLabelPane, Pos.TOP_LEFT);
		sceneLabelPane.setAlignment(Pos.TOP_LEFT);
		sceneLabel = new Label("Scene 0/50");
		sceneLabel.setFont(Font.loadFont(ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString(), 35));
		sceneLabel.setAlignment(Pos.CENTER);
		sceneLabel.setPadding(new Insets(30));
		sceneLabelPane.getChildren().add(sceneLabel);
		
		// Inventory
		StackPane inventoryHolderPane = new StackPane();
		inventoryHolderPane.setMaxWidth(150);
		inventoryHolderPane.setMinWidth(150);
		inventoryHolderPane.setMaxHeight(350);
		inventoryHolderPane.setMinHeight(350);
		inventoryPane = new InventoryPane();
		inventoryPane.setAlignment(Pos.CENTER);
		inventoryHolderPane.getChildren().add(inventoryPane);
		stackPane.getChildren().add(inventoryHolderPane);
		stackPane.setAlignment(inventoryHolderPane, Pos.BOTTOM_RIGHT);
		
		
		// Menu Bar
		HBox menuBarPane = new HBox();
		menuBarPane.setMaxWidth(200);
		menuBarPane.setMinWidth(200);
		menuBarPane.setMaxHeight(100);
		menuBarPane.setMinHeight(100);
		menuBarPane.setMargin(menuBarPane, getInsets());
		stackPane.getChildren().add(menuBarPane);
		stackPane.setAlignment(menuBarPane, Pos.TOP_RIGHT);
		menuBarPane.setAlignment(Pos.CENTER);
		
		BackgroundFill[] buttonStyle = new BackgroundFill[1];
		buttonStyle[0] = new BackgroundFill(Color.web("#a0a0a0",1.0), new CornerRadii(40), getInsets());
		
		BackgroundFill[] hoveredButtonStyle = new BackgroundFill[1];
		hoveredButtonStyle[0] = new BackgroundFill(Color.web("#c8c8c8",1.0), new CornerRadii(40), getInsets());
		
		BackgroundImage[] restartBgImg = new BackgroundImage[1];
		Image restartImg = new Image(ClassLoader.getSystemResource("img/menu/restart.png").toString());
		restartBgImg[0] = new BackgroundImage(restartImg, null, null, null, null);
		
		BackgroundImage[] menuBgImg = new BackgroundImage[1];
		Image menuImg = new Image(ClassLoader.getSystemResource("img/menu/menu.png").toString());
		menuBgImg[0] = new BackgroundImage(menuImg, null, null, null, null);
		
		StackPane restartPane = new StackPane();
		restartPane.setBackground(new Background(buttonStyle, restartBgImg));
		restartPane.setMaxWidth(80);
		restartPane.setMinWidth(80);
		restartPane.setMaxHeight(80);
		restartPane.setMinHeight(80);
		menuBarPane.setMargin(restartPane, new Insets(10));
		menuBarPane.getChildren().add(restartPane);
		
		StackPane menuPane = new StackPane();
		menuPane.setBackground(new Background(buttonStyle, menuBgImg));
		menuPane.setMaxWidth(80);
		menuPane.setMinWidth(80);
		menuPane.setMaxHeight(80);
		menuPane.setMinHeight(80);
		menuBarPane.setMargin(menuPane, new Insets(10));
		menuBarPane.getChildren().add(menuPane);
		
		
		
		// Event and Hovering Menu Bar Button
		restartPane.setOnMouseEntered(e -> {
			restartPane.setBackground(new Background(hoveredButtonStyle, restartBgImg));
		});
		
		restartPane.setOnMouseExited(e -> {
			restartPane.setBackground(new Background(buttonStyle, restartBgImg));
		});
		
		menuPane.setOnMouseEntered(e -> {
			menuPane.setBackground(new Background(hoveredButtonStyle, menuBgImg));
		});
		
		menuPane.setOnMouseExited(e -> {
			menuPane.setBackground(new Background(buttonStyle, menuBgImg));
		});
		
		
		restartPane.setOnMouseClicked(e -> {
			if (!GameLogic.isPlayerMoving()&&GameLogic.isGameRunning()) {
				AudioPlayer.clickAudio.play();
				GameLogic.restartLevel();
			}
		});
		
		menuPane.setOnMouseClicked(e -> {
			if (!GameLogic.isPlayerMoving()&&GameLogic.isGameRunning()) {
				AudioPlayer.clickAudio.play();
				GameLogic.setGameRunning(false);
				showMenu();
			}
		});
	}
	
	
	
	
	
	public void setStoryLabel(String text) {
		storyLabel.setText(text);
	}
	
	public void setSceneLabel(String text) {
		sceneLabel.setText(text);
	}
	
	public void buildMenu() {
		menuPane = new VBox(30);
		menuPane.setAlignment(Pos.CENTER);
		menuPane.setMinHeight(500);
		menuPane.setMaxHeight(500);
		menuPane.setMinWidth(600);
		menuPane.setMaxWidth(600);
		
		menuPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);"
				+ "-fx-background-radius: 30;"
				+ "-fx-border-radius: 30;");
		
		// Header
		Text headerText = new Text("Menu");
		Font headerFont = Font.loadFont(ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString(), 50);
		headerText.setFont(headerFont);
		menuPane.getChildren().add(headerText);
		
		// Button
		Button resumeButton = new MainMenuButton("Resume");
		Button restartButton = new MainMenuButton("Restart");
		Button exitButton = new MainMenuButton("Exit");
		
		menuPane.getChildren().add(resumeButton);
		menuPane.getChildren().add(restartButton);
		menuPane.getChildren().add(exitButton);
		
		resumeButton.setOnMouseClicked(e -> {
			AudioPlayer.clickAudio.play();
			hideMenu();
			GameLogic.setGameRunning(true);
		});
		
		restartButton.setOnMouseClicked(e -> {
			AudioPlayer.clickAudio.play();
			if (GameLogic.getGameplayMode() == 1) {
				GameLogic.restartLevel();
			}
			else {
				GameLogic.initTimerMode();
			}
		});
		
		exitButton.setOnMouseClicked(e -> {
			AudioPlayer.clickAudio.play();
			GameLogic.resetAnimationTimer();
			AudioPlayer.runMainMenuAudio();
			ScreenLogic.changeScene(new MainMenuPane());
			
		});
		
	}
	
	
	public void showMenu() {
		if (GameLogic.getGameplayMode() == 2) {
			GameLogic.getTimerThread().interrupt();
		}
		this.getChildren().add(menuPane);
		GameLogic.setMenuOpen(true);
	}
	
	public void hideMenu() {
		this.getChildren().remove(menuPane);
		GameLogic.setMenuOpen(false);
		if (GameLogic.getGameplayMode() == 2) {
			GameLogic.startCountDownTimer();
		}
		
	}
	
	public void showStoryModeClear() {
		VBox storyPane = new VBox(30);
		this.getChildren().add(storyPane);
		storyPane.setAlignment(Pos.CENTER);
		storyPane.setMinHeight(500);
		storyPane.setMaxHeight(500);
		storyPane.setMinWidth(600);
		storyPane.setMaxWidth(600);
		
		storyPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);"
				+ "-fx-background-radius: 30;"
				+ "-fx-border-radius: 30;");
		
		// Header
		Text headerText = new Text("Congratulation!");
		Font headerFont = Font.loadFont(ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString(), 42);
		headerText.setFont(headerFont);
		storyPane.getChildren().add(headerText);
		
		Text desText = new Text("You have completed story mode!");
		Font desFont = Font.loadFont(ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString(), 38);
		desText.setFont(desFont);
		storyPane.getChildren().add(desText);
		
		// Button
		Button restartButton = new MainMenuButton("Restart Story");
		Button exitButton = new MainMenuButton("Exit");
		
		storyPane.getChildren().add(restartButton);
		storyPane.getChildren().add(exitButton);
		
		restartButton.setOnMouseClicked(e -> {
			AudioPlayer.clickAudio.play();
			GameLogic.resetAnimationTimer();
			GameLogic.initNormalGame(1);
		});
		
		exitButton.setOnMouseClicked(e -> {
			AudioPlayer.clickAudio.play();
			GameLogic.resetAnimationTimer();
			AudioPlayer.runMainMenuAudio();
			ScreenLogic.changeScene(new MainMenuPane());
		});
	}
	
	public void showTimerModeEnd() {
		VBox timerPane = new VBox(30);
		this.getChildren().add(timerPane);
		timerPane.setAlignment(Pos.CENTER);
		timerPane.setMinHeight(500);
		timerPane.setMaxHeight(500);
		timerPane.setMinWidth(600);
		timerPane.setMaxWidth(600);
		
		timerPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);"
				+ "-fx-background-radius: 30;"
				+ "-fx-border-radius: 30;");
		
		// Header
		Text headerText = new Text("Time up!");
		Font headerFont = Font.loadFont(ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString(), 42);
		headerText.setFont(headerFont);
		timerPane.getChildren().add(headerText);
		
		Text desText = new Text("Your score is "+GameLogic.getScore());
		Font desFont = Font.loadFont(ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString(), 38);
		desText.setFont(desFont);
		timerPane.getChildren().add(desText);
		
		// Button
		Button restartButton = new MainMenuButton("Restart");
		Button exitButton = new MainMenuButton("Exit");
		
		timerPane.getChildren().add(restartButton);
		timerPane.getChildren().add(exitButton);
		
		restartButton.setOnMouseClicked(e -> {
			AudioPlayer.clickAudio.play();
			GameLogic.resetAnimationTimer();
			GameLogic.initTimerMode();
		});
		
		exitButton.setOnMouseClicked(e -> {
			AudioPlayer.clickAudio.play();
			GameLogic.resetAnimationTimer();
			AudioPlayer.runMainMenuAudio();
			ScreenLogic.changeScene(new MainMenuPane());
			
		});
	}
	
	public void showTutorialPane(int index) {
		VBox tutorialPane = new VBox(20);
		this.getChildren().add(tutorialPane);
		
		tutorialPane.setAlignment(Pos.CENTER);
		tutorialPane.setMinHeight(600);
		tutorialPane.setMaxHeight(600);
		tutorialPane.setMinWidth(800);
		tutorialPane.setMaxWidth(800);
		
		tutorialPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);"
				+ "-fx-background-radius: 30;"
				+ "-fx-border-radius: 30;");
		
		// ImgView
		ImageView imgView = new ImageView(new Image(ClassLoader.getSystemResource("img/tutorial/"+(index+1)+".png").toString()));
		tutorialPane.getChildren().add(imgView);
		
		// Button
		Button resumeButton = new MainMenuButton("Okay");
		
		tutorialPane.getChildren().add(resumeButton);

		
		resumeButton.setOnMouseClicked(e -> {
			AudioPlayer.clickAudio.play();
			this.getChildren().remove(tutorialPane);
			synchronized (GameLogic.getTutorialThread()){
			       
			    	   GameLogic.getTutorialThread().notify();
			        
	

		
			}
			//GameLogic.getTutorialThread().notify();
			GameLogic.setGameRunning(true);
		});
	}
}
