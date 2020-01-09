package Game;

import Main.Start;
import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class GameManager {
    public final static int TILE = 64;

    private  TileMap gameMap;
    private  Scene gameScene;
    private boolean gameContinue = true;

    public Scene initialize() {
        gameMap = new TileMap(Start.RESOLUTION_X, Start.RESOLUTION_Y);

        StackPane gamePane = new StackPane();
        Group tilemapGroup = new Group();

        tilemapGroup.getChildren().add(gameMap);
        gamePane.getChildren().add(tilemapGroup);

        gameScene = new Scene(gamePane);

        startGameLoop();
        return gameScene;
    }



    private void startGameLoop() {
        final LongProperty secondUpdate = new SimpleLongProperty(0);
        final LongProperty fpstimer = new SimpleLongProperty(0);
        final AnimationTimer mainTimer = new AnimationTimer() {
            int timer = 10;

            @Override
            public void handle(long timestamp) {
                if (timestamp / 1000000000 != secondUpdate.get()) {
                    timer--;
                    if(timer > 19) {
                    }
                    else if(timer <= 0){
                        timer = 30;
                    }
                }
                if(timestamp / 10000000 != fpstimer.get()) {
                }
                fpstimer.set(timestamp / 10000000);
                secondUpdate.set(timestamp / 1000000000);
            }
        };
        if(gameContinue) {
            mainTimer.start();
        } else {
            mainTimer.stop();
        }
    }
}
