package game.unit.projectile;

import game.unit.DamageableUnit;
import utils.Vector2D;

public class TargetProjectile extends Projectile {

    private final DamageableUnit target;
    private final int speed;

    public TargetProjectile(Vector2D position, DamageableUnit target, int speed, int width, int height, int damage) {
        super(position, target.getPosition(), speed, width, height, damage);
        this.target = target;
        this.speed = speed;
    }

    @Override
    public void move() {

        if (target.isDead()) {
            super.move();
            return;
        }

        Vector2D center = position.add(new Vector2D(target.getWidth() / 2.f, target.getHeight() / 2.f));
        Vector2D direction = target.getPosition().subtract(position).normalize();
        Vector2D velocity = direction.multiply(speed);
        setVelocity(velocity);

        super.move();
    }

}

