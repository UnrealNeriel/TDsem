package Game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class TownManager {
    private TownController townController;
    private int towerLimit = 5;
    private int millLevel = 1;
    private int quarryLevel = 1;
    private int hutLevel = 1;

    public Pane initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(TownManager.class.getResource("/townui.fxml"));
        Node townUI = loader.load(TownManager.class.getResource("/townui.fxml").openStream());

        Pane city = new Pane();

        townController = loader.getController();
        townController.setTownManager(this);

        ImageView mill = new ImageView();
        mill.setImage(new Image("sawmill.png"));
        mill.setFitWidth(150);
        mill.setFitHeight(150);
        mill.setX(25);
        mill.setY(300);
        mill.setOnMouseClicked(event -> {
            if(GameState.getGame().getGold() >= 20 * getMillLevel()) {
                GameState.getGame().setGold(GameState.getGame().getGold() - 20 * getMillLevel());
                setMillLevel(getMillLevel() + 1);
                System.out.println("Wood mill upgraded");
            } else {
                System.out.println("Not enough gold. Required: " + 20 * getMillLevel());
            }
        });

        ImageView farm = new ImageView();
        farm.setImage(new Image("farm.png"));
        farm.setFitWidth(150);
        farm.setFitHeight(150);
        farm.setX(25);
        farm.setY(500);
        farm.setOnMouseClicked(event -> {
            if (GameState.getGame().getGold() >= 25 * (getTowerLimit() - 4)) {
                GameState.getGame().setGold(GameState.getGame().getGold() - 25 * (getTowerLimit() - 4));
                setTowerLimit(getTowerLimit() + 1);
                System.out.println("Tower limit increased");
            } else {
                System.out.println("Not enough gold. Required: " + 25 * (getTowerLimit() - 4));
            }
        });

        ImageView quarry = new ImageView();
        quarry.setImage(new Image("Quarry.png"));
        quarry.setFitWidth(125);
        quarry.setFitHeight(125);
        quarry.setX(300);
        quarry.setY(320);
        quarry.setOnMouseClicked(event -> {
            if(GameState.getGame().getGold() >= 15 * getQuarryLevel()) {
                GameState.getGame().setGold(GameState.getGame().getGold() - 15 * getQuarryLevel());
                setQuarryLevel(getQuarryLevel() + 1);
                System.out.println("Stone quarry upgraded");
            } else {
                System.out.println("Not enough gold. Required: " + 15 * getQuarryLevel());
            }
        });

        ImageView hut = new ImageView();
        hut.setImage(new Image("round_rock_hut.png"));
        hut.setFitWidth(125);
        hut.setFitHeight(125);
        hut.setX(300);
        hut.setY(500);
        hut.setOnMouseClicked(event -> {
            if(GameState.getGame().getGold() >= 30 * getHutLevel()) {
                GameState.getGame().setGold(GameState.getGame().getGold() - 30 * getHutLevel());
                setHutLevel(getHutLevel() + 1);
                System.out.println("Iron hut upgraded");
            } else {
                System.out.println("Not enough gold. Required: " + 30 * getQuarryLevel());
            }
        });

        Label kamenolom = new Label("Pasivně přidává kámen");
        kamenolom.setLayoutX(310);
        kamenolom.setLayoutY(300);

        Label farma = new Label("Zvyšuje maximální počet věží");
        farma.setLayoutX(20);
        farma.setLayoutY(500);

        Label slevarna = new Label("Pasivně přidává železo");
        slevarna.setLayoutX(300);
        slevarna.setLayoutY(480);

        Label pila = new Label("Pasivně přidává dřevo");
        pila.setLayoutX(40);
        pila.setLayoutY(320);

        city.getChildren().add(new ImageView("/cityBackground.jpg"));
        city.getChildren().add(mill);
        city.getChildren().add(farm);
        city.getChildren().add(quarry);
        city.getChildren().add(hut);
        city.getChildren().add(pila);
        city.getChildren().add(kamenolom);
        city.getChildren().add(slevarna);
        city.getChildren().add(farma);
        city.getChildren().add(townUI);
        return city;
    }

    public TownController getTownController() {
        return townController;
    }

    public int getTowerLimit() {
        return towerLimit;
    }

    public void setTowerLimit(int towerLimit) {
        this.towerLimit = towerLimit;
    }

    public int getMillLevel() {
        return millLevel;
    }

    public void setMillLevel(int millLevel) {
        this.millLevel = millLevel;
    }

    public int getQuarryLevel() {
        return quarryLevel;
    }

    public void setQuarryLevel(int quarryLevel) {
        this.quarryLevel = quarryLevel;
    }

    public int getHutLevel() {
        return hutLevel;
    }

    public void setHutLevel(int hutLevel) {
        this.hutLevel = hutLevel;
    }
}
