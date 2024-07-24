package utils;

public record Vector2D(double x, double y) {

    public static double distance(Vector2D p1, Vector2D p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D multiply(int scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public Vector2D normalize() {
        double mag = magnitude();
        if (mag == 0) {
            return new Vector2D(0, 0);
        }
        return new Vector2D(x / mag, y / mag);
    }

    public Vector2D copy() {
        return new Vector2D(x, y);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public double distance(Vector2D other) {
        return distance(this, other);
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    @Override
    public String toString() {
        return "Vector2D(" + x + ", " + y + ")";
    }
}
