package utils;

public abstract class Movable {

    protected Vector2D position;
    protected Vector2D velocity;
    protected final int width;
    protected final int height;

    public Movable(Vector2D position, Vector2D velocity, int width, int height) {
        this.position = position;
        this.velocity = velocity;
        this.width = width;
        this.height = height;

    }

    static public boolean collides(Movable a, Movable b) {
        boolean xOverlap = a.position.getX() < b.position.getX() + b.width &&
                a.position.getX() + a.width > b.position.getX();

        boolean yOverlap = a.position.getY() < b.position.getY() + b.height &&
                a.position.getY() + a.height > b.position.getY();

        return xOverlap && yOverlap;
    }

    public void move() {
        position = position.add(velocity);
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public boolean collidesWith(Movable other) {
        return collides(this, other);
    }

    public Vector2D getPosition() {
        return position;
    }

}
