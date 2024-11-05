import java.awt.*;

public class Enemy {
    int x, y, hp;

    public Enemy(int x, int y, int hp) {
        this.x = x;
        this.y = y;
        this.hp = hp;
    }

    public void moveTowards(Player player) {
        if (player.x > x) x++;
        if (player.x < x) x--;
        if (player.y > y) y++;
        if (player.y < y) y--;
    }
}