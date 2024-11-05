import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class TopDownShooterGame extends JPanel implements ActionListener, KeyListener, MouseListener {
    Player player;
    ArrayList<Enemy> enemies;
    BossEnemy boss;
    Timer timer;
    JFrame frame;

    public TopDownShooterGame(JFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        player = new Player(400, 300, 20);
        enemies = new ArrayList<>();
        timer = new Timer(1000 / 60, this);
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
    }

    public void startGame(int stage) {
        timer.start();
        // Initialize enemies based on stage
        enemies.clear();
        int enemyCount = stage == 5 ? 10 : 10;
        for (int i = 0; i < enemyCount; i++) {
            enemies.add(new Enemy(new Random().nextInt(800), new Random().nextInt(600), 2));
        }

        // Add boss if stage 5
        if (stage == 5) {
            boss = new BossEnemy(400, 100, 15);
        } else {
            boss = null;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw player
        g.setColor(Color.BLUE);
        g.fillOval(player.x, player.y, 30, 30);

        // Draw enemies
        g.setColor(Color.RED);
        for (Enemy enemy : enemies) {
            g.fillOval(enemy.x, enemy.y, 20, 20);
        }

        // Draw boss if present
        if (boss != null) {
            g.setColor(Color.MAGENTA);
            g.fillOval(boss.x, boss.y, 50, 50);
        }

        // Draw Player HP
        g.setColor(Color.WHITE);
        g.drawString("HP: " + player.hp, 10, 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move(getWidth(), getHeight());
        for (Enemy enemy : enemies) {
            enemy.moveTowards(player);
            // Collision detection with player
            if (new Rectangle(player.x, player.y, 30, 30).intersects(new Rectangle(enemy.x, enemy.y, 20, 20))) {
                player.hp -= 4;
                enemies.remove(enemy);
                break;
            }
        }

        // Boss movement and shooting
        if (boss != null) {
            boss.moveTowards(player);
            if (boss.isShooting()) {
                // Implement shooting logic here (e.g., spawn projectiles)
            }
            // Collision detection with player
            if (new Rectangle(player.x, player.y, 30, 30).intersects(new Rectangle(boss.x, boss.y, 50, 50))) {
                player.hp -= 7;
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) player.movingLeft = true;
        if (key == KeyEvent.VK_D) player.movingRight = true;
        if (key == KeyEvent.VK_W) player.movingUp = true;
        if (key == KeyEvent.VK_S) player.movingDown = true;
        if (key == KeyEvent.VK_K) {
            attackEnemies();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) player.movingLeft = false;
        if (key == KeyEvent.VK_D) player.movingRight = false;
        if (key == KeyEvent.VK_W) player.movingUp = false;
        if (key == KeyEvent.VK_S) player.movingDown = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            // Shooting mechanic
            enemies.removeIf(enemy -> new Rectangle(enemy.x, enemy.y, 20, 20).contains(e.getPoint()));
            if (boss != null && new Rectangle(boss.x, boss.y, 50, 50).contains(e.getPoint())) {
                boss.hp -= 1;
                if (boss.hp <= 0) {
                    boss = null;
                }
            }
        } else if (SwingUtilities.isLeftMouseButton(e)) {
            attackEnemies();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    private void attackEnemies() {
        int attackRange = 100;
        enemies.removeIf(enemy -> {
            int distance = (int) Math.sqrt(Math.pow(enemy.x - player.x, 2) + Math.pow(enemy.y - player.y, 2));
            return distance <= attackRange;
        });
        if (boss != null) {
            int distance = (int) Math.sqrt(Math.pow(boss.x - player.x, 2) + Math.pow(boss.y - player.y, 2));
            if (distance <= attackRange) {
                boss.hp -= 1;
                if (boss.hp <= 0) {
                    boss = null;
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Top Down Shooter Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new CardLayout());

        MainMenu mainMenu = new MainMenu(frame);
        StageSelectionMenu stageSelectionMenu = new StageSelectionMenu(frame);

        frame.add(mainMenu, "MainMenu");
        frame.add(stageSelectionMenu, "StageSelection");

        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "MainMenu");
        frame.setVisible(true);
    }
}

//Hallo