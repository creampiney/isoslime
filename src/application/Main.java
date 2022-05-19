package application;
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
		ScreenLogic.init(stage);
	}
	
	@Override
    public void stop() {
        GameLogic.resetAnimationTimer();
    }
}
