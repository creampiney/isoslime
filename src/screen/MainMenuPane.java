package screen;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.AudioPlayer;
import logic.ScreenLogic;

public class MainMenuPane extends BasePane {
	

	private MainMenuScreen canvas;
	private VBox buttonUI;
	
	public MainMenuPane() {
		canvas = new MainMenuScreen(1280, 800);
		this.getChildren().add(canvas);
		canvas.draw();
		this.initUI();
	}
	
	public void draw() {
		canvas.draw();
	}
	
	public void initUI() {
		buttonUI = new VBox(10);
		Button startButton = new MainMenuButton("Start");
		Button helpButton = new MainMenuButton("Help");
		Button exitButton = new MainMenuButton("Exit");
		
		startButton.setOnAction(e -> {
			AudioPlayer.clickAudio.play();
			System.out.println("Start");
			ScreenLogic.changeScene(new ModeSelectorPane());
		});
		
		helpButton.setOnAction(e -> {
			AudioPlayer.clickAudio.play();
			System.out.println("Help");
			ScreenLogic.changeScene(new HelpPane());
		});
		
		exitButton.setOnAction(e -> {
			AudioPlayer.clickAudio.play();
			System.out.println("Exit");
			Platform.exit();
		});
		
		buttonUI.getChildren().add(startButton);
		buttonUI.getChildren().add(helpButton);
		buttonUI.getChildren().add(exitButton);
		
		
		Text text = new Text("IsoSlime");
		text.setFont(Font.loadFont(ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString(), 70));
		
		GridPane UIPane = new GridPane();
		GridPane.setHalignment(text, HPos.CENTER);
		GridPane.setHalignment(buttonUI, HPos.CENTER);
		UIPane.setVgap(200);
		UIPane.add(text, 0, 0);
		UIPane.add(buttonUI, 0, 1);
		UIPane.setAlignment(Pos.CENTER);
		this.getChildren().add(UIPane);
	}
}
