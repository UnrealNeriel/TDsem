package Game;

public class Monster{
    private static int TILE = 64;

    private int healthPoints;
    private int reward;
    private boolean isDead;
    private boolean pathFinished;

    public Monster(int healthPoints) {
        pathFinished = false;
        this.healthPoints = healthPoints;
        reward = 2;
    }

    public int getReward() {
        return reward;
    }

    public Monster getMonster() {
        return this;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isPathFinished() {
        return pathFinished;
    }

    public void takeDamage(int damage) {
        healthPoints = healthPoints - damage;
        if (healthPoints < 1) {
            isDead = true;
        }


    }
}
