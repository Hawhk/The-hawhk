package game.enemy;

import utils.Movable;
import utils.Vector2D;

import java.awt.*;

public class Enemy extends Movable {

    private int health;
    private boolean alive = true;

    public Enemy(int x, int y, int velocityX, int velocityY) {
        super(new Vector2D(x, y), new Vector2D(velocityX, velocityY), 20, 20);
        this.health = 100;
    }

    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        g.setColor(Color.RED);
        g.fillRect(position.getX() - width / 2, position.getY() - height / 2, width, height);
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("Enemy took " + damage + " damage. Health is now " + health);
        if (health <= 0) {
            alive = false;
        }
    }

    public void update() {
        if (!alive) {
            return;
        }
        move();
    }

    public boolean isAlive() {
        return alive;
    }
}
