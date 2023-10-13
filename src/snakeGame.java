import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private class Tile {
        int x;
        int y;
        
        Tile (int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    int frameWidth;
    int frameHeight;
    int tileSize = 25;  //px area

    Tile snakeHead;
    Tile food;
    Random random;

    //game logic
    Timer loop;
    int velocityX;
    int velocityY;

    SnakeGame(int frameWidth, int frameHeight){
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        setPreferredSize(new Dimension(this.frameWidth, this.frameHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        // Creates the tile and specifies the location in the grid.
        snakeHead = new Tile(5, 5);
        food = new Tile (10, 10);
        
        random = new Random();
        placeFood();

        velocityX =  0;
        velocityY = 0;

        loop = new Timer(100, this);
        loop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);        
    }

    public void draw(Graphics g){
        // Grid
        for (int i = 0; i < frameWidth / tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, frameHeight);
            g.drawLine(0, i * tileSize, frameWidth, i * tileSize);
        }

        // Food
        g.setColor(Color.red);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        // Snake
        g.setColor(Color.white);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y *tileSize, tileSize, tileSize);
    }

    public void placeFood() {
        food.x = random.nextInt(frameWidth / tileSize);
        food.y = random.nextInt(frameHeight / tileSize);
    }

    public void move() {
        //snake head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // The second condition prevents the snake from "stepping on its tail" when going up or down.
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            velocityX = -1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    // Do not need
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}