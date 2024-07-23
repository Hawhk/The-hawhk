package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener {

    private int playerX = 100;
    private int playerY = 100;

    public Game() {
        setPreferredSize(new Dimension(1920, 1080));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 20, 20);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                playerY -= 10;
                break;
            case KeyEvent.VK_S:
                playerY += 10;
                break;
            case KeyEvent.VK_A:
                playerX -= 10;
                break;
            case KeyEvent.VK_D:
                playerX += 10;
                break;
        }
        repaint();
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Game");
        Game game = new Game();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}