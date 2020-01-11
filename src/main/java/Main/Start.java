package Main;

import Game.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {

    public final static int RESOLUTION_X = 640;
    public final static int RESOLUTION_Y = 640;

    public static void main (String args[]) {
        launch(args);
    }
public void start(Stage stage) throws IOException {
        ImageView mill = new ImageView();
        Image image4 = new Image("sawmill.png");
        mill.setImage(image4);
        mill.setFitWidth(150);
        mill.setFitHeight(150);
        mill.setX(25);
        mill.setY(300);
    mill.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            System.out.println("akce");
        }
    });

        ImageView farm = new ImageView();
        Image image1 = new Image("farm.png");
        farm.setImage(image1);
        farm.setFitWidth(150);
        farm.setFitHeight(150);
        farm.setX(25);
        farm.setY(500);
    farm.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            System.out.println("akce");
        }
    });


        ImageView quarry = new ImageView();
        Image image2 = new Image("Quarry.png");
        quarry.setImage(image2);
        quarry.setFitWidth(125);
        quarry.setFitHeight(125);
        quarry.setX(300);
        quarry.setY(320);
    quarry.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            System.out.println("akce");
        }
    });

        ImageView hut = new ImageView();
        Image image3 = new Image("round_rock_hut.png");
        hut.setImage(image3);
        hut.setFitWidth(125);
        hut.setFitHeight(125);
        hut.setX(300);
        hut.setY(500);
    hut.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
           System.out.println("akce");
        }
    });

         Label kamenolom = new Label("Pasivně přidává kámen");
         kamenolom.setLayoutX(300);
         kamenolom.setLayoutY(290);


        Label farma = new Label("Zvyšuje maximální počet věží");
        farma.setLayoutX(15);
        farma.setLayoutY(490);


    Label slevarna = new Label("Pasivně přidává železo");
    slevarna.setLayoutX(300);
    slevarna.setLayoutY(490);


    Label pila = new Label("Pasivně přidává dřevo");
    pila.setLayoutX(15);
    pila.setLayoutY(290);




        final GameManager gameManager = new GameManager();


        StackPane TD = gameManager.initialize();

        SplitPane root = new SplitPane();

        Pane city = new Pane();
        city.getChildren().add(new ImageView("/cityBackground.jpg"));
        city.getChildren().add(mill);
        city.getChildren().add(farm);
        city.getChildren().add(quarry);
        city.getChildren().add(hut);
        city.getChildren().add(pila);
        city.getChildren().add(kamenolom);
        city.getChildren().add(slevarna);
        city.getChildren().add(farma);

    root.getItems().addAll(TD, city);


        Scene home = new Scene(root);

        stage.setTitle("VŠE defense");
        stage.setResizable(false);
        stage.setScene(home);
        stage.show();
        root.lookup(".split-pane-divider").setMouseTransparent(true);
    }
}