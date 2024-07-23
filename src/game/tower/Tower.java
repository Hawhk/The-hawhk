package game.tower;

import game.Game;
import game.enemy.Enemy;
import utils.Projectile;
import utils.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tower {

    private Vector2D position;
    private int width;
    private int height;
    private int damage;
    private int range;
    private int projectileSpeed;
    private float fireRate;
    private float firedCounter;
    private final Color color;
    private final List<Projectile> shots = new ArrayList<>();


    public Tower(int x, int y) {
        this.position = new Vector2D(x, y);
        this.width = 50;
        this.height = 50;
        this.damage = 10;
        this.range = 300;
        this.projectileSpeed = 10;
        this.fireRate = 0.6f;
        this.firedCounter = 0;
        this.color = Color.BLUE;
    }


    public void update(List<Enemy> enemies) {

        List<Projectile> shotsToRemove = new ArrayList<>();
        shots.forEach(Projectile::move);
        shots.forEach(shot -> enemies.forEach(enemy -> {
            if (shot.collidesWith(enemy)) {
                shot.dealDamage(enemy);
                shotsToRemove.add(shot);
            }
        }));
        shots.stream().filter(Projectile::isOutOfBounds).forEach(shotsToRemove::add);
        shots.removeAll(shotsToRemove);
        List<Enemy> enemiesInRange = getEnemiesInRange(enemies);
        enemiesInRange.stream().findFirst().ifPresent(this::fire);

    }


    public void draw(Graphics g) {

        g.setColor(color);
        g.fillRect(position.getX() - width / 2, position.getY() - height / 2, width, height);


        shots.forEach(shot -> shot.draw(g));

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(position.getX() - range, position.getY() - range, 2 * range, 2 * range);
    }

    public List<Enemy> getEnemiesInRange(List<Enemy> enemies) {
        List<Enemy> enemiesInRange = new ArrayList<>();
        for (Enemy enemy : enemies) {
            double dist = position.distance(enemy.getPosition());
            if (dist <= range) {
                enemiesInRange.add(enemy);
            }
        }
        return enemiesInRange;
    }

    public void fire(Enemy enemy) {
        if (firedCounter == 0) {
            double dist = position.distance(enemy.getPosition());
            if (dist <= range) {
                Vector2D direction = enemy.getPosition().subtract(position).normalize();
                Vector2D velocity = direction.multiply(projectileSpeed);
                shots.add(new Projectile(position.copy(), velocity, 10, 10, damage));
            }

        }

        firedCounter += 1.0f / Game.TARGET_FPS;
        if (firedCounter >= fireRate) {
            firedCounter = 0;
        }
    }
}
