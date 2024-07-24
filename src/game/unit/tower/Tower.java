package game.unit.tower;

import game.Game;
import game.unit.enemy.Enemy;
import game.unit.projectile.Projectile;
import utils.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tower {

    private final Vector2D position;
    private final Color color;
    private final List<Projectile> shots = new CopyOnWriteArrayList<>();

    private int width;
    private int height;
    private int damage;
    private int range;
    private int projectileSpeed;
    private int projectileWidth;
    private int projectileHeight;

    private float fireRate;
    private float firedCounter;


    public Tower(int x, int y) {
        this.position = new Vector2D(x, y);
        this.width = 50;
        this.height = 50;
        this.damage = 10;
        this.range = 300;
        this.projectileSpeed = 10;
        this.projectileWidth = 10;
        this.projectileHeight = 10;
        this.fireRate = 0.6f;
        this.firedCounter = 0;
        this.color = Color.BLUE;
    }


    public void update(List<Enemy> enemies) {

        List<Projectile> shotsToRemove = new CopyOnWriteArrayList<>();
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

        if (firedCounter > 0) {
            increaseFiredCounter();
        }
    }


    public void draw(Graphics g) {

        g.setColor(color);
        g.fillRect(position.getX() - width / 2, position.getY() - height / 2, width, height);


        shots.forEach(shot -> shot.draw(g));

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        if (firedCounter > 0) {
            g2d.setColor(Color.RED);
        }
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

    private void fire(Enemy enemy) {
        if (firedCounter == 0) {
            shots.add(new Projectile(position.copy(), enemy.getPosition(), projectileSpeed, projectileWidth, projectileHeight, damage));
            increaseFiredCounter();
        }
    }

    private void increaseFiredCounter() {
        firedCounter += 1.0f / Game.TARGET_FPS;
        if (firedCounter >= fireRate) {
            firedCounter = 0;
        }
    }
}
