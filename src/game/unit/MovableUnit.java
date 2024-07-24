package game.unit;

import utils.Moveable;
import utils.Vector2D;

public abstract class MovableUnit extends Unit implements Moveable {

    private Vector2D velocity;

    public MovableUnit(Vector2D position, Vector2D velocity, int width, int height) {

        super(position, width, height);

        this.velocity = velocity;

    }

    @Override
    public void move() {
        position = position.add(velocity);
    }

    @Override
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}
