package game.unit;

import utils.Vector2D;

import java.awt.*;

public abstract class Unit {

    protected Vector2D position;
    protected final int width;
    protected final int height;

    public Unit(Vector2D position, int width, int height) {
        this.position = position;

        this.width = width;
        this.height = height;

    }

    static public boolean collides(Unit a, Unit b) {
        boolean xOverlap = a.position.getX() < b.position.getX() + b.width &&
                a.position.getX() + a.width > b.position.getX();

        boolean yOverlap = a.position.getY() < b.position.getY() + b.height &&
                a.position.getY() + a.height > b.position.getY();

        return xOverlap && yOverlap;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(position.getX() - width / 2, position.getY() - height / 2, width, height);
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public boolean collidesWith(Unit other) {
        return collides(this, other);
    }

    public Vector2D getPosition() {
        return position;
    }

}
