package game.unit;

import utils.Damageable;
import utils.Vector2D;

public abstract class DamageableUnit extends Unit implements Damageable {

    protected int health;
    protected int maxHealth;

    public DamageableUnit(Vector2D position, int width, int height, int health) {

        super(position, width, height);

        this.position = position;
        this.health = health;
        this.maxHealth = health;

    }

    @Override
    public void takeDamage(int damage) {
        if (isDead()) {
            return;
        }
        health -= damage;
        System.out.println("Unit took " + damage + " damage. Health: " + health);
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }
}
