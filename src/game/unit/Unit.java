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
        int leftA = a.position.getX() - a.width / 2;
        int rightA = a.position.getX() + a.width / 2;
        int topA = a.position.getY() - a.height / 2;
        int bottomA = a.position.getY() + a.height / 2;

        int leftB = b.position.getX() - b.width / 2;
        int rightB = b.position.getX() + b.width / 2;
        int topB = b.position.getY() - b.height / 2;
        int bottomB = b.position.getY() + b.height / 2;

        return rightA >= leftB && leftA <= rightB && bottomA >= topB && topA <= bottomB;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(position.getX() - width / 2, position.getY() - height / 2, width, height);
    }

    public boolean collidesWith(Unit other) {
        return collides(this, other);
    }

    public Vector2D getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
