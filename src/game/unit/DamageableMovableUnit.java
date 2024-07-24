package game.unit;

import utils.Moveable;
import utils.Vector2D;

import java.awt.*;

public abstract class DamageableMovableUnit extends DamageableUnit implements Moveable {

    private Vector2D velocity;

    public DamageableMovableUnit(Vector2D position, int width, int height, int health, Vector2D velocity) {

        super(position, width, height, health);

        this.velocity = velocity;

    }

    @Override
    public void draw(Graphics g) {
        if (isDead()) {
            return;
        }
        super.draw(g);
    }

    @Override
    public void move() {
        if (isDead()) {
            return;
        }
        position = position.add(velocity);
    }

    @Override
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}
