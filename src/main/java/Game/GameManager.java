package Game;

import Main.Start;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * Class GameManager
 * Tato Class inicializuje prvky ze kterých se hra skládá
 */
public class GameManager {
    public final static int TILE = 64;

    private static TileMap gameMap;
    private Group monsterLayer;
    private static GameState game;
    private GameController gameController;
    private StackPane gamePane;
    private boolean gameContinue = true;
    private static TownManager townManager;
    /**
     * Vytváří jednotlivé prvky hry
     * @return
     * @throws IOException
     */
    public StackPane initialize(TownManager TM) throws IOException {
        init(TM);
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(updateResources(), 0, 1, TimeUnit.SECONDS);
        startGameLoop();
        return gamePane;
    }

    public StackPane initialize(TownManager TM, String file) throws IOException {
        init(TM);
        String[] tokens = file.split("\\s+");
        if (!tokens[0].equals("eI>c;Vr!s-='`UFk<H!J")) {
            System.exit(666);
        }
        int index = 1;
        int tmp;
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++, index++) {
                tmp = parseInt(tokens[index]);
                if (tmp == 7) {
                    game.addTower(new Tower(x, y));
                    gameMap.setMapNode(x, y, 7);
                } else if (tmp == 8) {
                    game.addTower(new Tower(x, y));
                    for (Tower tower : game.getPlayerTowers()) {
                        if (tower.getNodeX() == x && tower.getNodeY() == y) {
                            tower.setAttackDamage(tower.getAttackDamage() * 2);
                            tower.setAttackRange(tower.getAttackRange() + 40);
                        }
                    }
                    gameMap.setMapNode(x, y, 8);
                } else if (tmp == 9) {
                    game.addTower(new Tower(x, y));
                    for (Tower tower : game.getPlayerTowers()) {
                        if (tower.getNodeX() == x && tower.getNodeY() == y) {
                            tower.setAttackDamage(tower.getAttackDamage() * 4);
                            tower.setAttackRange(tower.getAttackRange() + 80);
                        }
                    }
                    gameMap.setMapNode(x, y, 9);
                } else if (tmp == 10) {
                    game.addTower(new Tower(x, y));
                    for (Tower tower : game.getPlayerTowers()) {
                        if (tower.getNodeX() == x && tower.getNodeY() == y) {
                            tower.setAttackDamage(tower.getAttackDamage() * 8);
                            tower.setAttackRange(tower.getAttackRange() + 120);
                        }
                    }
                    gameMap.setMapNode(x, y, 10);
                } else if (tmp == 11) {
                    game.addTower(new Tower(x, y));
                    for (Tower tower : game.getPlayerTowers()) {
                        if (tower.getNodeX() == x && tower.getNodeY() == y) {
                            tower.setAttackDamage(tower.getAttackDamage() * 16);
                            tower.setAttackRange(tower.getAttackRange() + 160);
                        }
                    }
                    gameMap.setMapNode(x, y, 11);
                }
            }
        }
        tmp = parseInt(tokens[index]);
        index++;
        for (int i = 0; i < tmp; i++, index+=3) {
            game.getMonstersAlive().add(new Monster(parseInt(tokens[index + 2]), parseInt(tokens[index]), parseInt(tokens[index + 1])));
            monsterLayer.getChildren().add(game.getMonstersAlive().get(game.getMonstersAlive().size() - 1).getMonster());
        }

        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(updateResources(), 0, 1, TimeUnit.SECONDS);
        startGameLoop();
        return gamePane;
    }

    private void init(TownManager TM) throws IOException {
        townManager = TM;
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
    }

    /**
     * Metoda aktualizuje suroviny
     * @return
     */
    private Runnable updateResources() {
        return () -> {
            game.setIron(game.getIron() + townManager.getHutLevel());
            game.setWood(game.getWood() + townManager.getMillLevel());
            game.setStone(game.getStone() + townManager.getQuarryLevel());
        };
    }
    /**
     * Metoda vrací prostor se hrou
     * @return
     */
    public StackPane getGamePane() {
        return gamePane;
    }
    /**
     * Metoda umožňuje koupit tower
     * @param xCords
     * @param yCords
     */
    public void buyTower(double xCords , double yCords) {
        if (game.getTowerCount() == townManager.getTowerLimit()) {
            System.out.println("Tower limit reached");
            return;
        }

        int xTile = (int)(xCords / TILE), yTile = (int)(yCords / TILE);

        if(gameMap.nodeOpen(xTile, yTile) && game.getGold() > 9) {
            game.addTower(new Tower(xTile, yTile));
            game.setGold(game.getGold() - 10);
            gameMap.setMapNode(xTile, yTile, 7);
        } else {
            System.out.println("Not enough gold or no more space");
        }
    }
    /**
     * Metoda odstraňuje tower
     * @param xCords
     * @param yCords
     */
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

    /**
     * Metoda vylepšuje tower o jednu úroveň
     * @param xCords
     * @param yCords
     */
    public void upgradeTower(double xCords, double yCords) {
        int xTile = (int) (xCords / TILE), yTile = (int) (yCords / TILE);
        int tmp = gameMap.getNodeValue(xTile, yTile);
        for (Tower tower : game.getPlayerTowers()) {
            if (tower.getNodeX() == xTile && tower.getNodeY() == yTile) {
                switch (tmp) {
                    case 7:
                        if (game.getGold() > 24 && game.getIron() > 24 && game.getStone() > 24 && game.getWood() > 24) {
                            game.setGold(game.getGold() - 25);
                            game.setIron(game.getIron() - 25);
                            game.setWood(game.getWood() - 25);
                            game.setStone(game.getStone() - 25);
                            tower.setAttackDamage(tower.getAttackDamage() * 2);
                            tower.setAttackRange(tower.getAttackRange() + 40);
                            gameMap.setMapNode(xTile, yTile, tmp + 1);
                        } else {System.out.println("Not enough resources");}
                        break;
                    case 8:
                        if (game.getGold() > 99 && game.getIron() > 99 && game.getStone() > 99 && game.getWood() > 99) {
                            game.setGold(game.getGold() - 100);
                            game.setIron(game.getIron() - 100);
                            game.setWood(game.getWood() - 100);
                            game.setStone(game.getStone() - 100);
                            tower.setAttackDamage(tower.getAttackDamage() * 2);
                            tower.setAttackRange(tower.getAttackRange() + 40);
                            gameMap.setMapNode(xTile, yTile, tmp + 1);
                        } else {System.out.println("Not enough resources");}
                        break;
                    case 9:
                        if (game.getGold() > 499 && game.getIron() > 499 && game.getStone() > 499 && game.getWood() > 499) {
                            game.setGold(game.getGold() - 500);
                            game.setIron(game.getIron() - 500);
                            game.setWood(game.getWood() - 500);
                            game.setStone(game.getStone() - 500);
                            tower.setAttackDamage(tower.getAttackDamage() * 2);
                            tower.setAttackRange(tower.getAttackRange() + 40);
                            gameMap.setMapNode(xTile, yTile, tmp + 1);
                        } else {System.out.println("Not enough resources");}
                        break;
                    case 10:
                        if (game.getGold() > 2499 && game.getIron() > 2499 && game.getStone() > 2499 && game.getWood() > 2499) {
                            game.setGold(game.getGold() - 2500);
                            game.setIron(game.getIron() - 2500);
                            game.setWood(game.getWood() - 2500);
                            game.setStone(game.getStone() - 2500);
                            tower.setAttackDamage(tower.getAttackDamage() * 2);
                            tower.setAttackRange(tower.getAttackRange() + 40);
                            gameMap.setMapNode(xTile, yTile, tmp + 1);
                        } else {System.out.println("Not enough resources");}
                        break;
                    default:
                        System.out.println("Nothing to upgrade here");
                }
            }
        }
    }
    /**
     * Metoda vytváří Monster
     * @param health
     */
    private void createMonster(int health) {
        game.getMonstersAlive().add(new Monster(health));
        monsterLayer.getChildren().add(game.getMonstersAlive().get(game.getMonstersAlive().size() - 1).getMonster());
    }
    /**
     * Metoda aktualizuje pozice monster
     */
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
    /**
     * Metoda animuje projektily tower
     */
    private void createProjectiles() {
        Path projectilePath;
        PathTransition animation;
        for(Tower tower : game.getPlayerTowers()) {
            for(Projectile projectile : tower.getProjectileList()) {
                projectilePath = new Path(new MoveTo(projectile.getStartX() , projectile.getStartY()));
                projectilePath.getElements().add(new LineTo(projectile.getEndX() , projectile.getEndY()));
                animation = new PathTransition(Duration.millis(300) , projectilePath , projectile);

                animation.setOnFinished(actionEvent -> {
                    PathTransition finishedAnimation = (PathTransition) actionEvent.getSource();
                    Projectile finishedProjectile = (Projectile) finishedAnimation.getNode();

                    finishedProjectile.setVisible(false);
                    monsterLayer.getChildren().remove(finishedProjectile);

                    if(finishedProjectile.getTarget().isDead()){
                        removeMonster(finishedProjectile.getTarget());
                    }
                });
                monsterLayer.getChildren().add(projectile);
                animation.play();
            }
            tower.getProjectileList().clear();
        }
    }
    /**
     * Metoda aktualizuje čas kola
     * @param timer
     */
    private void updateLabels(int timer) {
        gameController.updateTime(Integer.toString(timer));

        townManager.getTownController().updateResources(
                Integer.toString(game.getGold()),
                Integer.toString(game.getIron()),
                Integer.toString(game.getWood()),
                Integer.toString(game.getStone()),
                Integer.toString(game.getTowerCount()),
                Integer.toString(game.getLives()),
                Integer.toString(game.getLevel()));
    }
    /**
     * Metoda odstraňuje monstra z hracího pole
     * @param monster
     */
    private  void removeMonster(Monster monster) {
        if (monster.isPathFinished()) {
            game.setLives((game.getLives()) - 1);
        } else {
            game.setGold((game.getGold()) + monster.getReward() * game.getLevel());
        }
        monster.setVisible(false);
        game.getMonstersAlive().remove(monster);
    }
    /**
     * Metoda zahajuje Loop na posílání monster
     */
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
                        createMonster(120 * game.getLevel());
                    }
                    else if(timer <= 0){
                        game.setLevel(game.getLevel() + 1);
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

    public static void saveGame() {
        SimpleDateFormat dateFormat;
        Date date;
        String frmtdDate;
        BufferedWriter out;

        System.out.println("Game saved");

        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        date = new Date();
        frmtdDate = "TDsem " + dateFormat.format(date);
        frmtdDate += ".txt";
        int tmp;

        try {
            FileWriter fstream = new FileWriter("C:/Users/HP GAMING 17/Desktop/" + frmtdDate, true);
            out = new BufferedWriter(fstream);
            out.write("eI>c;Vr!s-='`UFk<H!J");
            out.newLine();
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    out.write(GameManager.gameMap.getNodeValue(y, x) + " ");
                }
                out.newLine();
            }

            tmp = GameManager.game.getMonstersAlive().size();
            out.write(tmp + "");
            out.newLine();

            for (Monster monster : GameManager.game.getMonstersAlive()) {
                out.write(monster.getX() + " ");
                out.write(monster.getY() + " ");
                out.write(monster.getHealthPoints() + "");
                out.newLine();
            }
            out.newLine();

            out.write(GameManager.game.getGold() + " ");
            out.write(GameManager.game.getIron() + " ");
            out.write(GameManager.game.getWood() + " ");
            out.write(GameManager.game.getStone() + " ");
            out.write(GameManager.game.getLives() + " ");
            out.write(GameManager.game.getLevel() + " ");
            out.write(GameManager.townManager.getTowerLimit() + " ");
            out.write(GameManager.townManager.getHutLevel() + " ");
            out.write(GameManager.townManager.getMillLevel() + " ");
            out.write(GameManager.townManager.getQuarryLevel() + "");
            out.newLine();

            out.write("wO$Lf]VZ}J82C~?<7P*,");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -2;
        }
    }
}