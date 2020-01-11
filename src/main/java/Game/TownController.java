package Game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TownController {
    @FXML
    public Label gold;
    @FXML
    public Label iron;
    @FXML
    public Label wood;
    @FXML
    public Label stone;
    @FXML
    public Label towers;
    @FXML
    public Label lives;
    @FXML
    public Label level;

    private TownManager townManager;

    public void setTownManager(TownManager townManager) {
        this.townManager = townManager;
    }

    public void updateResources(String gold, String iron, String wood, String stone, String towers, String lives, String level) {
        this.gold.setText(gold);
        this.iron.setText(iron);
        this.wood.setText(wood);
        this.stone.setText(stone);
        this.towers.setText(towers + "/" + townManager.getTowerLimit());
        this.lives.setText(lives);
        this.level.setText(level);
    }
}