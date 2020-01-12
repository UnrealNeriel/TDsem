package Main;

import Game.GameManager;
import Game.TownManager;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

        home.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.F5) {
                GameManager.saveGame();
            }
        });
        Start.stage.setScene(home);
    }

    /**
     * Metoda načítá hru aplikace
     */
    public void loadGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load game");
        File file = fileChooser.showOpenDialog(Start.stage);
        StringBuilder builder = new StringBuilder();
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    builder.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        startLoadedGame(builder.toString());
    }

    /**
     * pomocná metoda při načítání uložené hry ze souborů
     * @param file
     */
    public void startLoadedGame(String file) {
        try{
            GameManager gameManager = new GameManager();
            TownManager townManager = new TownManager();
            Pane city = townManager.initialize();
            StackPane TD = gameManager.initialize(townManager, file);
            SplitPane root = new SplitPane();
            root.getItems().addAll(TD, city);
            home = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        home.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.F5) {
                GameManager.saveGame();
            }
        });
        Start.stage.setScene(home);
    }

    /**
     * Metoda zavírá aplikaci
     */
    public void exitGame(){
        System.exit(1);
    }
}
