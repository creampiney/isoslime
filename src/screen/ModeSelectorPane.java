package screen;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.AudioPlayer;
import logic.GameLogic;
import logic.ScreenLogic;

public class ModeSelectorPane extends BasePane {
	
	private static final String defaultFont = ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString();
	
	private MainMenuScreen canvas;
	private GridPane UIPane;
	
	public ModeSelectorPane() {
		canvas = new MainMenuScreen(1280, 800);
		this.getChildren().add(canvas);
		this.draw();
		this.initUI();
	}
	
	public void draw() {
		canvas.draw();
	}
	
	public void initUI() {
		// GridPane BG Box
		UIPane = new GridPane();
		//UIPane.setGridLinesVisible(true);
		UIPane.setAlignment(Pos.CENTER);
		UIPane.setMinHeight(600);
		UIPane.setMaxHeight(600);
		UIPane.setMinWidth(1000);
		UIPane.setMaxWidth(1000);
		UIPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);"
				+ "-fx-background-radius: 30;"
				+ "-fx-border-radius: 30;");
		this.setUIConstraint();
		this.getChildren().add(UIPane);
		
		// Header
		Text headerText = new Text("Choose Mode");
		Font headerFont = Font.loadFont(defaultFont, 50);
		headerText.setFont(headerFont);
		UIPane.add(headerText, 0, 0);
		
		// Slot GridPane
		this.initSlotGrid();
		
		// Back Button
		MainMenuButton backButton = new MainMenuButton("Back");
		this.getChildren().add(backButton);
		this.setAlignment(backButton, Pos.BOTTOM_LEFT);
		this.setPadding(new Insets(10));
		
		backButton.setOnMouseClicked(e -> {
			AudioPlayer.clickAudio.play();
			GameLogic.resetAnimationTimer();
			ScreenLogic.changeScene(new MainMenuPane());
		});
		
		
	}
	
	public void setUIConstraint() {
		RowConstraints rc0 = new RowConstraints();
		rc0.setPercentHeight(17);
		rc0.setValignment(VPos.CENTER);
		RowConstraints rc1 = new RowConstraints();
		rc1.setPercentHeight(83);
		rc1.setValignment(VPos.CENTER);
		ColumnConstraints cc0 = new ColumnConstraints();
		cc0.setPercentWidth(100);
		cc0.setHalignment(HPos.CENTER);
		
		UIPane.getRowConstraints().addAll(rc0, rc1);
		UIPane.getColumnConstraints().addAll(cc0);
	}
	
	private void initSlotGrid() {
		GridPane slotGridPane = new GridPane();
		//slotGridPane.setGridLinesVisible(true);
		slotGridPane.setAlignment(Pos.CENTER);
		slotGridPane.setHgap(50);
		UIPane.add(slotGridPane, 0, 1);
		
		// Constraint
		RowConstraints rc0 = new RowConstraints();
		rc0.setPercentHeight(15);
		rc0.setValignment(VPos.CENTER);
		RowConstraints rc1 = new RowConstraints();
		rc1.setPercentHeight(40);
		rc1.setValignment(VPos.CENTER);
		RowConstraints rc2 = new RowConstraints();
		rc2.setPercentHeight(45);
		rc2.setValignment(VPos.CENTER);
				
		ColumnConstraints cc0 = new ColumnConstraints();
		cc0.setPercentWidth(100);
		cc0.setHalignment(HPos.CENTER);

		
		
		GridPane slotPane1 = new GridPane();
		//slotPane1.setGridLinesVisible(true);
		slotPane1.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5);"
				+ "-fx-background-radius: 20;"
				+ "-fx-border-radius: 20;");
		slotPane1.setMinHeight(370);
		slotPane1.setMaxHeight(370);
		slotPane1.setMinWidth(270);
		slotPane1.setMaxWidth(270);
		
		slotPane1.getRowConstraints().addAll(rc0, rc1, rc2);
		slotPane1.getColumnConstraints().addAll(cc0);
		
		Text headText1 = new Text("Story Mode");
		headText1.setFont(Font.loadFont(defaultFont, 30));
		slotPane1.add(headText1, 0, 0);
		
		Image slotImg1 = new Image(ClassLoader.getSystemResource("img/menu/normalmode.png").toString());
		PixelReader reader1 = slotImg1.getPixelReader();
		WritableImage slotCropImg1 = new WritableImage(reader1, 0, 0, 120, 83);
		ImageView slotImgView1 = new ImageView(slotCropImg1);
		slotImgView1.resize(100, 100);
		slotPane1.add(slotImgView1, 0, 1);
		
		Text desText1 = new Text("The short story about slime finding his friend with 50 scenes!");
		desText1.setWrappingWidth(250);
		desText1.setFont(Font.loadFont(defaultFont, 30));
		slotPane1.add(desText1, 0, 2);
			
		slotGridPane.add(slotPane1, 0, 0);
		
		
		
		
		GridPane slotPane2 = new GridPane();
		//slotPane2.setGridLinesVisible(true);
		slotPane2.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5);"
				+ "-fx-background-radius: 20;"
				+ "-fx-border-radius: 20;");
		slotPane2.setMinHeight(370);
		slotPane2.setMaxHeight(370);
		slotPane2.setMinWidth(270);
		slotPane2.setMaxWidth(270);
		
		slotPane2.getRowConstraints().addAll(rc0, rc1, rc2);
		slotPane2.getColumnConstraints().addAll(cc0);
		
		Text headText2 = new Text("Puzzle Mode");
		headText2.setFont(Font.loadFont(defaultFont, 30));
		slotPane2.add(headText2, 0, 0);
		
		Image slotImg2 = new Image(ClassLoader.getSystemResource("img/menu/puzzlemode.png").toString());
		//PixelReader reader2 = slotImg2.getPixelReader();
		//WritableImage slotCropImg2 = new WritableImage(reader2, 0, 80, 200, 120);
		ImageView slotImgView2 = new ImageView(slotImg2);
		slotImgView2.resize(100, 100);
		slotPane2.add(slotImgView2, 0, 1);
		
		Text desText2 = new Text("Beat as many levels as you can within 2 minutes. 30 seconds will be added every levels completed!");
		desText2.setWrappingWidth(250);
		desText2.setFont(Font.loadFont(defaultFont, 30));
		slotPane2.add(desText2, 0, 2);
			
		slotGridPane.add(slotPane2, 1, 0);
		
		slotPane1.setOnMouseEntered(e -> {
			slotPane1.setStyle("-fx-background-color: rgba(0, 100, 100, 1);"
					+ "-fx-background-radius: 20;"
					+ "-fx-border-radius: 20;");
		});
		
		slotPane1.setOnMouseExited(e -> {
			slotPane1.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5);"
					+ "-fx-background-radius: 20;"
					+ "-fx-border-radius: 20;");
		});
		
		slotPane1.setOnMouseClicked(e -> {
			AudioPlayer.clickAudio.play();
			GameLogic.setGameplayMode(1);
			GameLogic.initNormalGame(1);
			AudioPlayer.runGameAudio();
		});
		
		slotPane2.setOnMouseEntered(e -> {
			slotPane2.setStyle("-fx-background-color: rgba(0, 100, 100, 1);"
					+ "-fx-background-radius: 20;"
					+ "-fx-border-radius: 20;");
		});
		
		slotPane2.setOnMouseExited(e -> {
			slotPane2.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5);"
					+ "-fx-background-radius: 20;"
					+ "-fx-border-radius: 20;");
		});
		
		slotPane2.setOnMouseClicked(e -> {
			AudioPlayer.clickAudio.play();
			GameLogic.setGameplayMode(2);
			GameLogic.initTimerMode();
			AudioPlayer.runGameAudio();
			
		});
			
		
	}
	
	
	
}
