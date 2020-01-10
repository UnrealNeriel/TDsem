package Game;

import Main.Start;
import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;

import java.util.Iterator;

public class GameManager {
    public final static int TILE = 64;

    private  TileMap gameMap;
    private  Group monsterLayer;
    private GameState game;
    private boolean gameContinue = true;

    public StackPane initialize() {
        game = new GameState();
        GameState.init(game);
        gameMap = new TileMap(Start.RESOLUTION_X, Start.RESOLUTION_Y);

        StackPane gamePane = new StackPane();
        Group tilemapGroup = new Group();
        monsterLayer = new Group();
        monsterLayer.getChildren().add(tilemapGroup);

        tilemapGroup.getChildren().add(gameMap);
        gamePane.getChildren().add(monsterLayer);

        Monster.setPath(gameMap.getPath());
        startGameLoop();
        return gamePane;
    }

    private void createMonster(int health) {
        game.getMonstersAlive().add(new Monster(health));
        monsterLayer.getChildren().add(game.getMonstersAlive().get(game.getMonstersAlive().size() - 1).getMonster());
    }

    private void updateLocations() {
        for (Iterator<Monster> monsters = game.getMonstersAlive().iterator(); monsters.hasNext(); ) {
            Monster monster = monsters.next();
            if (monster.isPathFinished()) {
                monsters.remove();
                removeMonster(monster);
            } else if (!monster.isDead()) {
                monster.updateLocation(1);
            }
        }
    }

    private  void removeMonster(Monster monster) {
        monster.setVisible(false);
        game.getMonstersAlive().remove(monster);
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
                        createMonster(200);
                    }
                    else if(timer <= 0){
                        timer = 30;
                    }
                }
                if(timestamp / 10000000 != fpstimer.get()) {
                    updateLocations();
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
