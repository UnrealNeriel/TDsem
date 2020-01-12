package Main;

import Game.GameManager;
import Game.TownManager;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/**
 * MainMenuController vytváří prostředí menu aplikace
 */
public class MainMenuController {
    private Scene home;

    /**
     * Metoda spouští novou hru aplikace a zakládá scénu s hrací plochou
     */
    public void startNewGame(){
        try{
            GameManager gameManager = new GameManager();
            TownManager townManager = new TownManager();
            Pane city = townManager.initialize();
            StackPane TD = gameManager.initialize(townManager);
            SplitPane root = new SplitPane();
            root.getItems().addAll(TD, city);
            //root.lookup(".split-pane-divider").setMouseTransparent(true);
            home = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Start.stage.setScene(home);
    }

    /**
     * Metoda zavírá aplikaci
     */
    public void exitGame(){
        System.exit(1);
    }
}
