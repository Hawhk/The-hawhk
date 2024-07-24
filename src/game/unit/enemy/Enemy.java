package game.unit.enemy;

import game.unit.DamageableMovableUnit;
import utils.Vector2D;

import java.awt.*;

public class Enemy extends DamageableMovableUnit {

    public Enemy(int x, int y, int velX, int velY, int width, int height, int health) {
        super(new Vector2D(x, y), width, height, health, new Vector2D(velX, velY));
        this.health = health;
    }

    public void draw(Graphics g) {
        if (isDead()) {
            return;
        }
        g.setColor(Color.RED);
        g.fillRect(position.getX() - width / 2, position.getY() - height / 2, width, height);
    }


    public void update() {
        move();
    }
}
