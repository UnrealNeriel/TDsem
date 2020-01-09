package Game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Projectile extends Circle {
    private final Monster target;
    private final int startX;
    private final int startY;

    Projectile(Monster target , int towerX , int towerY){
        super(towerX , towerY , 16);
        this.target = target;
        this.startX = towerX;
        this.startY = towerY;
    }

    public Monster getTarget(){
        return target;
    }

    public int getStartX(){
        return startX;
    }
    public int getStartY(){
        return startY;
    }
}

