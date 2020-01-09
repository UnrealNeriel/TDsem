package Game;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Monster extends Circle {
    private static int TILE = 64;

    private static ArrayList<Point2D> path;
    private int healthPoints;
    private int reward;
    private int nodeDirection;
    private boolean moveX;
    private boolean isDead;
    private boolean pathFinished;

    public Monster(int healthPoints) {
        super(path.get(0).getX() * TILE + TILE / 2, path.get(0).getY() * TILE + TILE / 2, 16);
        pathFinished = false;
        moveX = true;
        nodeDirection = 1;
        this.healthPoints = healthPoints;
        reward = 2;
    }

    public int getX(){
        return (int) this.getCenterX();
    }
    public int getY(){
        return (int) this.getCenterY();
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

    public static void setPath(ArrayList<Point2D> pathSet){
        path = pathSet;
    }

    public void takeDamage(int damage) {
        healthPoints = healthPoints - damage;
        if (healthPoints < 1) {
            isDead = true;
        }
    }

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
