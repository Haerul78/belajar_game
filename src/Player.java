import java.awt.*;

public class Player {
    int x, y, hp;
    boolean movingLeft, movingRight, movingUp, movingDown;
    int directionX, directionY;

    public Player(int x, int y, int hp) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.directionX = 1;
        this.directionY = 0;
    }

    public void move(int width, int height) {
        if (movingLeft) {
            x -= 5;
            directionX = -1;
            directionY = 0;
        }
        if (movingRight) {
            x += 5;
            directionX = 1;
            directionY = 0;
        }
        if (movingUp) {
            y -= 5;
            directionX = 0;
            directionY = -1;
        }
        if (movingDown) {
            y += 5;
            directionX = 0;
            directionY = 1;
        }

        // Loop area mechanics
        if (x < 0) x = width;
        if (x > width) x = 0;
        if (y < 0) y = height;
        if (y > height) y = 0;
    }
}
//player
//main
//haerul
//kl
//><dasda