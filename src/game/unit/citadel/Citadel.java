package game.unit.citadel;

import game.unit.DamageableUnit;
import utils.Vector2D;

import java.awt.*;

public class Citadel extends DamageableUnit {

    public Citadel(Vector2D position, int width, int height, int health) {
        super(position, width, height, health);
    }

    @Override
    public void draw(Graphics g) {
        if (isDead()) {
            return;
        }
        g.setColor(Color.ORANGE);
        g.fillRect(position.getX(), position.getY(), width, height);
    }

}
