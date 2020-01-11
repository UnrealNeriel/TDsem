package Game;

import Main.Start;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Iterator;

public class GameManager {
    public final static int TILE = 64;

    private TileMap gameMap;
    private Group monsterLayer;
    private GameState game;
    private GameController gameController;
    private StackPane gamePane;
    private boolean gameContinue = true;

    public StackPane initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(GameManager.class.getResource("/gameui.fxml"));
        game = new GameState();
        GameState.init(game);
        gameMap = new TileMap(Start.RESOLUTION_X, Start.RESOLUTION_Y);

        gamePane = new StackPane();
        Group tilemapGroup = new Group();
        monsterLayer = new Group();
        monsterLayer.getChildren().add(tilemapGroup);

        tilemapGroup.getChildren().add(gameMap);
        gamePane.getChildren().add(monsterLayer);

        Node gameUI = loader.load(GameManager.class.getResource("/gameui.fxml").openStream());
        gamePane.getChildren().add(gameUI);

        gameController = loader.getController();
        gameController.setGameManager(this);

        Monster.setPath(gameMap.getPath());
        startGameLoop();
        return gamePane;
    }

    public StackPane getGamePane() {
        return gamePane;
    }

    public void buyTower(double xCords , double yCords) {
        int xTile = (int)(xCords / TILE), yTile = (int)(yCords / TILE);

        if(gameMap.nodeOpen(xTile, yTile)) {
            game.addTower(new Tower(xTile, yTile));
            gameMap.setMapNode(xTile, yTile, 7);
        }
    }

    public void removeTower(double xCords , double yCords) {
        int xTile = (int)(xCords / TILE), yTile = (int)(yCords / TILE);

        if (!gameMap.nodeOpen(xTile, yTile)) {
            for (Tower tower : game.getPlayerTowers()) {
                if (tower.getNodeX() == xTile && tower.getNodeY() == yTile) {
                    game.removeTower(tower);
                    gameMap.setMapNode(xTile, yTile, 0);
                    break;
                }
            }
        }
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

    private void createProjectiles() {
        Path projectilePath;
        PathTransition animation;
        for(Tower tower : game.getPlayerTowers()) {
            for(Projectile projectile : tower.getProjectileList()) {
                projectilePath = new Path(new MoveTo(projectile.getStartX() , projectile.getStartY()));
                projectilePath.getElements().add(new LineTo(projectile.getEndX() , projectile.getEndY()));
                animation = new PathTransition(Duration.millis(300) , projectilePath , projectile);

                animation.setOnFinished(new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(ActionEvent actionEvent) {
                        PathTransition finishedAnimation = (PathTransition) actionEvent.getSource();
                        Projectile finishedProjectile = (Projectile) finishedAnimation.getNode();

                        finishedProjectile.setVisible(false);
                        monsterLayer.getChildren().remove(finishedProjectile);

                        if(finishedProjectile.getTarget().isDead()){
                            removeMonster(finishedProjectile.getTarget());
                        }
                    }
                });
                monsterLayer.getChildren().add(projectile);
                animation.play();
            }
            tower.getProjectileList().clear();
        }
    }

    private void updateLabels(int timer) {
        gameController.updateTime(Integer.toString(timer));
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
                createProjectiles();
                if(timestamp / 10000000 != fpstimer.get()) {
                    updateLocations();
                }
                fpstimer.set(timestamp / 10000000);
                secondUpdate.set(timestamp / 1000000000);
                updateLabels(timer);
            }
        };
        if(gameContinue) {
            mainTimer.start();
        } else {
            mainTimer.stop();
        }
    }

    public GameManager getGameManager() {
        return this;
    }
}
