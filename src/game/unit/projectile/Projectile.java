package game.unit.projectile;

import game.Game;
import game.unit.DamageableUnit;
import game.unit.MovableUnit;
import utils.Vector2D;

import java.awt.*;

public class Projectile extends MovableUnit {

    private final int damage;
    private final Color color;

    public Projectile(Vector2D position, Vector2D target, int speed, int width, int height, int damage) {
        super(position, new Vector2D(0, 0), width, height);
        this.damage = damage;
        this.color = Color.GREEN;

        Vector2D direction = target.subtract(position);
        setVelocity(direction.normalize().multiply(speed));
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(position.getX() - width / 2, position.getY() - height / 2, width, height);
    }

    public void dealDamage(DamageableUnit unit) {
        unit.takeDamage(damage);
    }

    public boolean isOutOfBounds() {
        return position.getX() < 0 || position.getX() > Game.WINDOW_WITH || position.getY() < 0 || position.getY() > Game.WINDOW_HEIGHT;
    }

}