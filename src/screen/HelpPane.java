package screen;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.AudioPlayer;
import logic.GameLogic;
import logic.ScreenLogic;

public class HelpPane extends BasePane {
	
	private static final String[] buttonString = {"Movement","Keyboard","Game Interface","Crate","Crate in the Hole","Items","Axe","Wood","Limitation for Wood","Magic Wand","Limitation for Magic Wand","Sensor","A Trick for Sensor","Ice","Some Trick for Ice"};
	
	private static final String defaultFont = ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString();
	
	private MainMenuScreen canvas;
	private GridPane UIPane;
	
	public HelpPane() {
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
		UIPane.setMinWidth(1100);
		UIPane.setMaxWidth(1100);
		UIPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);"
				+ "-fx-background-radius: 30;"
				+ "-fx-border-radius: 30;");
		this.setUIConstraint();
		this.getChildren().add(UIPane);
		
		// Header
		Text headerText = new Text("Help / Tutorial");
		Font headerFont = Font.loadFont(defaultFont, 40);
		headerText.setFont(headerFont);
		UIPane.add(headerText, 0, 0, 2, 1);
		
		// button Box
		ScrollPane buttonScrollPane = new ScrollPane();
		VBox buttonVBox = new VBox(20);
		buttonScrollPane.setContent(buttonVBox);
		UIPane.add(buttonScrollPane, 0, 1);
		this.initButton(buttonVBox);
		
		buttonScrollPane.getStylesheets().add(ClassLoader.getSystemResource("data/style/scrollbox.css").toString());
		
		buttonVBox.setAlignment(Pos.CENTER);
		
		buttonVBox.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
        	buttonScrollPane.getViewportBounds().getWidth(), buttonScrollPane.viewportBoundsProperty()));
		
		
		
		
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
		rc0.setPercentHeight(15);
		rc0.setValignment(VPos.CENTER);
		RowConstraints rc1 = new RowConstraints();
		rc1.setPercentHeight(85);
		rc1.setValignment(VPos.CENTER);
		ColumnConstraints cc0 = new ColumnConstraints();
		cc0.setPercentWidth(25);
		cc0.setHalignment(HPos.CENTER);
		ColumnConstraints cc1 = new ColumnConstraints();
		cc1.setPercentWidth(75);
		cc1.setHalignment(HPos.CENTER);
		
		UIPane.getRowConstraints().addAll(rc0, rc1);
		UIPane.getColumnConstraints().addAll(cc0, cc1);
	}
	
	private void initButton(VBox buttonVBox) {
		for (int i = 0; i < 15; i++) {
			MainMenuButton eachButton = new MainMenuButton(buttonString[i]);
			eachButton.setPrefWidth(200);
			eachButton.setFont(Font.loadFont(ClassLoader.getSystemResource("fonts/FC_daisy_regular.ttf").toString(), 25));
			int index = i;
			eachButton.setOnMouseClicked(e -> {
				AudioPlayer.clickAudio.play();
				Node result = null;
			    ObservableList<Node> childrens = UIPane.getChildren();
			    for(Node node : childrens) {
			        if(node instanceof ImageView && UIPane.getRowIndex(node) == 1 && UIPane.getColumnIndex(node) == 1) {
			            result = node;
			            UIPane.getChildren().remove(result);
			            break;
			        }
			    }
				UIPane.add(new ImageView(new Image(ClassLoader.getSystemResource("img/tutorial/"+(index+1)+".png").toString())), 1, 1);
			});
			buttonVBox.getChildren().add(eachButton);
		}
	}
}
