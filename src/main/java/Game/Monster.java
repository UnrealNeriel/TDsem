package Game;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
/**
 * Class Monster popisuje Monstra ve hře
 * Class Monster je součástí aplikace
 * Class Monster slouží k vytvoření chodících monster po plánu hry
 */
public class Monster extends Circle {
    private static int TILE = 64;

    private static ArrayList<Point2D> path;
    private int healthPoints;
    private int reward;
    private int nodeDirection;
    private boolean moveX;
    private boolean isDead;
    private boolean pathFinished;
    /**
     * Konstruktor Monster
     * @param healthPoints
     */
    public Monster(int healthPoints) {
        super(path.get(0).getX() * TILE + TILE / 2, path.get(0).getY() * TILE + TILE / 2, 16);
        this.setFill(new ImagePattern(new Image("/bomb.png")));
        pathFinished = false;
        moveX = true;
        nodeDirection = 1;
        this.healthPoints = healthPoints;
        reward = 2;
    }
    /**
     * Metoda vrací souřadnici X polohy monstra
     * @return
     */
    public int getX(){
        return (int) this.getCenterX();
    }
    /**
     * Metoda vrací souřadnici Y polohy monstra
     * @return
     */
    public int getY(){
        return (int) this.getCenterY();
    }
    /**
     * Metoda vrací reward za zabití monstra
     * @return
     */
    public int getReward() {
        return reward;
    }
    /**
     * Metoda vrací monstrum
     * @return
     */
    public Monster getMonster() {
        return this;
    }
    /**
     * Metoda vrací jestli je monstrum mrtvé
     * @return
     */
    public boolean isDead() {
        return isDead;
    }
    /**
     * Metoda vrací jestli monstrum došlo na konec cesty
     * @return
     */

    public boolean isPathFinished() {
        return pathFinished;
    }
    /**
     * Metoda nastavuje cestu pro monstra
     * @param pathSet
     */

    public static void setPath(ArrayList<Point2D> pathSet){
        path = pathSet;
    }
    /**
     * Metoda ubírá životy monster a označuje je jako mrtvé pokud životy klesnou na 0
     * @param damage
     */
    public void takeDamage(int damage) {
        healthPoints = healthPoints - damage;
        if (healthPoints < 1) {
            isDead = true;
        }
    }
    /**
     * Metoda aktualizuje umístění monster na mapě
     * @param distance
     */
    public void updateLocation(int distance) {
        if(moveX) {
            this.setCenterX(this.getCenterX() + distance);
            if(this.getCenterX() == path.get(nodeDirection).getX() * TILE + TILE / 2) {
                moveX = false;
                nodeDirection++;
                if(nodeDirection == path.size()) {
                    pathFinished = true;
                    isDead = true;
                }
            }
        } else {
            if(this.getCenterY() < path.get(nodeDirection).getY() * TILE + TILE / 2) {
                this.setCenterY(this.getCenterY() + distance);
            } else {
                this.setCenterY(this.getCenterY() - distance);
            }
            if(this.getCenterY() == path.get(nodeDirection).getY() * TILE + TILE / 2) {
                moveX = true;
                nodeDirection++;
                if(nodeDirection == path.size()){
                    pathFinished = true;
                    isDead = true;
                }
            }
        }
    }
}
