package Game;

import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * Class tower popisuje towery ve hře
 * Třída je součástí aplikace
 * Třída vytváří towery
 */
public class Tower {
    private final static int TILE = GameManager.TILE;

    private int attackDamage;
    private int attackSpeed;
    private int attackRange;
    private ArrayList<Projectile> projectileList;
    private Point2D coord;
    /**
     * Konstruktor zakládající towery
     * @param y
     */
    public Tower(int x, int y) {
        projectileList = new ArrayList<Projectile>();
        coord = new Point2D(x, y);
        attackDamage = 5;
        attackSpeed = 500;
        attackRange = 200;
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(shootingThread(), 0, attackSpeed, TimeUnit.MILLISECONDS);
    }
    /**
     * Metoda přidává projektil který tower střílí na monstra
     * @param target
     */
    public void createProjectile(Monster target) {
        projectileList.add(new Projectile(target, getX(), getY()));
    }
    /**
     * Metoda vrací X souřadnice na mapě
     * @return
     */
    public int getX() {
        return (int) coord.getX() * TILE + TILE / 2;
    }
    /**
     * Metoda vrací Y souřadnice na mapě
     * @return
     */
    public int getY() {
        return (int) coord.getY() * TILE + TILE / 2;
    }
    /**
     * Metoda vrací X souřadnice na mapě
     * @return
     */
    public int getNodeX() {
        return (int) coord.getX();
    }
    /**
     * Metoda vrací Y souřadnice na mapě
     * @return
     */
    public int getNodeY() {
        return (int) coord.getY();
    }

    /**
     * Metoda vrací útočné poškození věže
     * @return
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * Metoda nastavuje útočné poškození věže
     * @param attackDamage
     */
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * Metoda vrací dostřel věže
     * @return
     */
    public int getAttackRange() {
        return attackRange;
    }

    /**
     * Metoda nastavuje dostřel tower
     * @param attackRange
     */
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    /**
     * Metoda vrací list s umístěním projektilu na mapě
     * @return
     */
    public ArrayList<Projectile> getProjectileList() {
        return projectileList;
    }

    private Runnable shootingThread() {
        return () -> {
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
        };
    }
}