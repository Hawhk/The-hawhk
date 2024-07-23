package utils;

import game.Game;
import game.enemy.Enemy;

import java.awt.*;

public class Projectile extends Movable {

    private final int damage;
    private final Color color;

    public Projectile(Vector2D position, Vector2D velocity, int width, int height, int damage) {
        super(position, velocity, 10, 10);
        this.damage = 10;
        this.color = Color.GREEN;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(position.getX() - width / 2, position.getY() - height / 2, width, height);
    }

    public void dealDamage(Enemy enemy) {
        enemy.takeDamage(damage);
    }

    public boolean isOutOfBounds() {
        return position.getX() < 0 || position.getX() > Game.WINDOW_WITH || position.getY() < 0 || position.getY() > Game.WINDOW_HEIGHT;
    }

}