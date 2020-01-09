package Main;

import Game.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {
    public final static int RESOLUTION_X = 640;
    public final static int RESOLUTION_Y = 640;

    public static void main (String args[]) {
        launch(args);
    }

    public void start(Stage stage) {
        GameManager gameManager = new GameManager();
        Scene home = gameManager.initialize();
        stage.setScene(home);
        stage.show();
    }
}