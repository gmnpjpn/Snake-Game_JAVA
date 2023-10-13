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

    // Snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;


    // Food
    Tile food;
    Random random;

    //game logic
    Timer loop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;

    SnakeGame(int frameWidth, int frameHeight){
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        setPreferredSize(new Dimension(this.frameWidth, this.frameHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        // Creates the tile and specifies the location in the grid.
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

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

        // Snake head
        g.setColor(Color.white);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y *tileSize, tileSize, tileSize);

        // Snake body
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
        }

        // Score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameOver) {
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }
        else {
            g.drawString("Score: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }
    }

    public void placeFood() {
        food.x = random.nextInt(frameWidth / tileSize);
        food.y = random.nextInt(frameHeight / tileSize);
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return (tile1.x == tile2.x && tile1.y == tile2.y);
    }

    public void move() {
        //eat food
        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        // Snake body
        for (int i = snakeBody.size()-1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }

        }

        //snake head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // game over conditions
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);

            // Collide with the snake head.
            if (collision(snakeHead, snakePart)) {
                gameOver = true;

            }
        }

        if (snakeHead.x * tileSize < 0 || snakeHead.x > frameWidth || snakeHead.y * tileSize < 0 || snakeHead.y > frameHeight) {
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            loop.stop();
        }
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