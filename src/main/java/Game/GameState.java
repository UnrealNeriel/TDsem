package Game;

import java.util.ArrayList;

public class GameState {
    public static final int IS_RUNNING = 1;

    private static GameState playerGame;
    private int state;
    private int gold;
    private int iron;
    private int wood;
    private int stone;
    private int lives;
    private int level;
    private ArrayList<Monster> monstersAlive;
    private ArrayList<Tower> playerTowers;

    public GameState() {
        gold = 100;
        iron = 0;
        wood = 0;
        stone = 0;
        lives = 100;
        level = 0;
        state = IS_RUNNING;
        monstersAlive = new ArrayList<Monster>();
        playerTowers = new ArrayList<Tower>();
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
    public void setIron(int iron) {
        this.iron = iron;
    }
    public void setWood(int wood) {
        this.wood = wood;
    }
    public void setStone(int stone) {
        this.stone = stone;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getGold() {
        return gold;
    }
    public int getIron() {
        return iron;
    }
    public int getWood() {
        return wood;
    }
    public int getStone() {
        return stone;
    }
    public int getLives() {
        return lives;
    }
    public int getLevel() {
        return level;
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

    public int getTowerCount() {
        return playerTowers.size();
    }

    public void addMonster(Monster monster){monstersAlive.add(monster);}
    public void addTower(Tower tower){playerTowers.add(tower);}
    public void removeMonster(Monster monster){monstersAlive.remove(monster);}
    public void removeTower(Tower tower){playerTowers.remove(tower);}
}
