package Game;

import java.util.ArrayList;

public class GameState {
    public static final int IS_RUNNING = 1;

    private static GameState playerGame;
    private int state;
    private ArrayList<Monster> monstersAlive;

    public GameState() {
        state = IS_RUNNING;
        monstersAlive = new ArrayList<Monster>();
    }

    public static void init(GameState gameState) {
        playerGame = gameState;
    }

    public ArrayList<Monster> getMonstersAlive() {
        return monstersAlive;
    }
}
