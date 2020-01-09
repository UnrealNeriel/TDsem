package Main;

import Game.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Start extends Application {
    public final static int RESOLUTION_X = 640;
    public final static int RESOLUTION_Y = 640;

    public static void main (String args[]) {
        launch(args);
    }

    public void start(Stage stage) {
        GameManager gameManager = new GameManager();

        StackPane TD = gameManager.initialize();

        SplitPane root = new SplitPane();

        Pane city = new Pane();
        city.getChildren().add(new ImageView("/cityBackground.jpg"));

        root.getItems().addAll(TD, city);


        Scene home = new Scene(root);

        stage.setTitle("VŠE defense");
        stage.setResizable(false);
        stage.setScene(home);
        stage.show();
    }
}