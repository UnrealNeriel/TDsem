package Game;

import java.util.ArrayList;

public class GameState {
    public static final int IS_RUNNING = 1;

    private static GameState playerGame;
    private int state;
    private ArrayList<Monster> monstersAlive;
    private ArrayList<Tower> playerTowers;

    public GameState() {
        state = IS_RUNNING;
        monstersAlive = new ArrayList<Monster>();
        playerTowers = new ArrayList<Tower>();
    }

    public static void init(GameState gameState) {
        playerGame = gameState;
    }

    public static GameState getGame() {
        return playerGame;
    }

    public ArrayList<Tower> getPlayerTowers(){
        return playerTowers;
    }

    public ArrayList<Monster> getMonstersAlive() {
        return monstersAlive;
    }

    public void addMonster(Monster monster){monstersAlive.add(monster);}
    public void addTower(Tower tower){playerTowers.add(tower);}
    public void removeMonster(Monster monster){monstersAlive.remove(monster);}
    public void removeTower(Tower tower){playerTowers.remove(tower);}
}
