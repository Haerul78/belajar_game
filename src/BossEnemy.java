import java.awt.*;

public class BossEnemy extends Enemy {
    boolean isShooting;
    int shootCounter;

    public BossEnemy(int x, int y, int hp) {
        super(x, y, hp);
        isShooting = false;
        shootCounter = 0;
    }

    @Override
    public void moveTowards(Player player) {
        if (!isShooting) {
            if (player.x > x) x++;
            if (player.x < x) x--;
            if (player.y > y) y++;
            if (player.y < y) y--;
            shootCounter++;
            if (shootCounter > 360) { // After 6 seconds (60 frames per second * 6)
                isShooting = true;
                shootCounter = 0;
            }
        } else {
            shootCounter++;
            if (shootCounter > 180) { // Shoot for 3 seconds
                isShooting = false;
                shootCounter = 0;
            }
        }
    }

    public boolean isShooting() {
        return isShooting;
    }
}