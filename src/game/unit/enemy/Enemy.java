package game.unit.enemy;

import game.unit.DamageableMovableUnit;
import game.unit.DamageableUnit;
import game.unit.Unit;
import utils.Vector2D;

import java.awt.*;

public class Enemy extends DamageableMovableUnit {

    private final int speed;
    private final int damage;

    public Enemy(int x, int y, int speed, int width, int height, int health) {
        super(new Vector2D(x, y), width, height, health, new Vector2D(0, 0));
        this.health = health;
        this.speed = speed;
        this.damage = 5;
    }

    public void draw(Graphics g) {
        if (isDead()) {
            return;
        }
        g.setColor(Color.RED);
        g.fillRect(position.getX() - width / 2, position.getY() - height / 2, width, height);
    }


    public void update(DamageableUnit unit) {

        if (isDead() || unit.isDead()) {
            return;
        }

        Vector2D heightWidth = new Vector2D(unit.getWidth() / 2.f, unit.getHeight() / 2.f);
        Vector2D direction = unit.getPosition().add(heightWidth).subtract(position).normalize();
        setVelocity(direction.multiply(speed));
        move();

        if (Unit.collides(unit, this)) {
            health = 0;
            dealDamage(unit);
        }
    }

    public void dealDamage(DamageableUnit unit) {
        unit.takeDamage(damage);
    }
}
