import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.ScreenLogic;

public class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		
		// Real Cmd
		ScreenLogic.init(stage);
		
		/*
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 1280, 800);
		stage.setScene(scene);
		stage.setTitle("Test Final Project");
		stage.getIcons().add(new Image(ClassLoader.getSystemResource("rickroll.png").toString()));
		stage.setResizable(false);
		stage.show();
		 
		Player player = new Player(0,0,0,0);
		Map map = new Map("map.txt");
		
		GameScreen canvas = new GameScreen(1280, 800, player, map);
		root.getChildren().add(canvas);
		canvas.requestFocus();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				canvas.draw();
				InputUtility.updateInputState();
			}
		};
		animation.start();
		*/
	}
	
	@Override
    public void stop() {
        GameLogic.resetAnimationTimer();
    }
}
