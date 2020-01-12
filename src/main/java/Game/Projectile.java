package Game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
/**
 * Class Projectille popisuje projektil tower
 */
public class Projectile extends Circle {
    private final Monster target;
    private final int startX;
    private final int startY;
    /**
     * Konstruktor nastavuje hodnoty tower
     * @param target
     * @param towerX
     * @param towerY
     */
    Projectile(Monster target , int towerX , int towerY){
        super(towerX , towerY , 16);
        this.setFill(new ImagePattern(new Image("/projectile.png")));
        this.target = target;
        this.startX = towerX;
        this.startY = towerY;
    }
    /**
     * Metoda vrací monster na který může tower střílet
     * @return
     */
    public Monster getTarget(){
        return target;
    }
    /**
     * Metoda vrací konec souřadnice X kam padá
     * @return
     */
    public int getEndX(){
        return target.getX();
    }
    /**
     * Metoda vrací konec souřadnice Y kam padá
     * @return
     */
    public int getEndY(){
        return target.getY();
    }
    /**
     * Metoda vrací začátek souřadnice X odkud vychází
     * @return
     */
    public int getStartX(){
        return startX;
    }
    /**
     * Metoda vrací začátek souřadnice Y odkud vychází
     * @return
     */
    public int getStartY(){
        return startY;
    }
}