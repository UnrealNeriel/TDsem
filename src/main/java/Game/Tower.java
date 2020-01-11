package Game;

import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tower {
    private final static int TILE = GameManager.TILE;

    private int attackDamage;
    private int attackSpeed;
    private int attackRange;
    private ArrayList<Projectile> projectileList;
    private Point2D coord;

    public Tower(int x, int y) {
        projectileList = new ArrayList<Projectile>();
        coord = new Point2D(x, y);
        attackDamage = 5;
        attackSpeed = 500;
        attackRange = 200;
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(shootingThread(), 0, attackSpeed, TimeUnit.MILLISECONDS);
    }

    public void createProjectile(Monster target) {
        projectileList.add(new Projectile(target, getX(), getY()));
    }

    public int getX() {
        return (int) coord.getX() * TILE + TILE / 2;
    }

    public int getY() {
        return (int) coord.getY() * TILE + TILE / 2;
    }

    public int getNodeX() {
        return (int) coord.getX();
    }

    public int getNodeY() {
        return (int) coord.getY();
    }

    public ArrayList<Projectile> getProjectileList() {
        return projectileList;
    }

    private Runnable shootingThread() {
        return new Runnable() {
            public void run() {
                int towerMinXRange = getX() - attackRange, towerMaxXRange = getX() + attackRange,
                    towerMinYRange = getY() - attackRange, towerMaxYRange = getY() + attackRange;

                for (Monster monster : GameState.getGame().getMonstersAlive()) {
                    if (monster.getX() > towerMinXRange && monster.getX() < towerMaxXRange &&
                            monster.getY() > towerMinYRange && monster.getY() < towerMaxYRange) {
                        createProjectile(monster);
                        monster.takeDamage(attackDamage);
                        break;
                    }
                }
            }
        };
    }
}