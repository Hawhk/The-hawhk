package game;

import game.unit.enemy.Enemy;
import game.unit.tower.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends JPanel implements Runnable, MouseListener {

    private boolean isRunning = true;
    private Thread gameThread;

    public static final int TARGET_FPS = 60;
    private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

    public static int WINDOW_WITH = 1920;
    public static int WINDOW_HEIGHT = 1080;

    private final List<Tower> towers = new CopyOnWriteArrayList<>();
    private final List<Enemy> enemies = new CopyOnWriteArrayList<>();

    public Game() {
        setPreferredSize(new Dimension(WINDOW_WITH, WINDOW_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addMouseListener(this);
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long lastTimer = System.currentTimeMillis();
        int frames = 0;

        while (isRunning) {
            long now = System.nanoTime();
            long updateLength = now - lastTime;
            lastTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);

            updateGame();
            repaint();
            frames++;

            // Sleep to maintain the FPS
            long sleepTime = (lastTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Game loop interrupted", e);
                }
            }

            // Print FPS to console every second
            if (System.currentTimeMillis() - lastTimer > 1000) {
                lastTimer += 1000;
                System.out.println("Last second FPS: " + frames);
                frames = 0;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        towers.forEach(tower -> {
            tower.draw(g);
        });
        enemies.forEach(enemy -> {
            enemy.draw(g);
        });
    }

    private void updateGame() {
        towers.forEach(tower -> tower.update(enemies));
        List<Enemy> removable = enemies.stream().filter(Enemy::isDead).toList();
        enemies.removeAll(removable);
        enemies.forEach(Enemy::update);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        Game game = new Game();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        game.startGame();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1) {
            System.out.println("Mouse pressed at " + e.getX() + ", " + e.getY());
            towers.add(new Tower(e.getX(), e.getY()));
        } else {
            System.out.println("Left mouse button pressed at " + e.getX() + ", " + e.getY());
            enemies.add(new Enemy(e.getX(), e.getY(), (int) (Math.random() * 10 - 5), (int) (Math.random() * 10 - 5), 50, 50, 50));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        System.out.println("Mouse released at " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered at " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited at " + e.getX() + ", " + e.getY());
    }
}