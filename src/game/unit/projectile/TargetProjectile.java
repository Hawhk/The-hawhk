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

        Vector2D targetPosition = target.getPosition();
        Vector2D direction = targetPosition.subtract(position);
        Vector2D velocity = direction.normalize().multiply(speed);
        setVelocity(velocity);

        super.move();
    }

}

