package Game;

import javafx.geometry.Point2D;

import java.util.ArrayList;

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
        }

        public void createProjectile(Monster target) {
        projectileList.add(new Projectile(target , getX() , getY()));
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




}
