package Game;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
/**
 * Class GameController - třída upravuje fxml soubor
 * CLass rozšiřuje intuitivnost aplikace přidáním klikacích obrázků
 */
public class GameController {
    @FXML
    private Label timeLabel;

    private GameManager gameManager;

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void buyTower() {
        gameManager.getGamePane().setOnMouseClicked(new buyTower());
    }

    public void removeTower() {
        gameManager.getGamePane().setOnMouseClicked(new removeTower());
    }

    public void upgradeTower() {
        gameManager.getGamePane().setOnMouseClicked(new upgradeTower());
    }

    public void updateTime(String time) {
        this.timeLabel.setText(time);
    }

    private class buyTower implements EventHandler<MouseEvent> {
        public void handle(MouseEvent me) {
            gameManager.buyTower(me.getX(), me.getY());
        }
    }
    private class removeTower implements EventHandler<MouseEvent> {
        public void handle(MouseEvent me) {
            gameManager.removeTower(me.getX(), me.getY());
        }
    }
    private class upgradeTower implements EventHandler<MouseEvent> {
        public void handle(MouseEvent me) {
            gameManager.upgradeTower(me.getX(), me.getY());
        }
    }
}