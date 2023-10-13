import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel{

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

    SnakeGame(int frameWidth, int frameHeight){
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        setPreferredSize(new Dimension(this.frameWidth, this.frameHeight));
        setBackground(Color.black);

        // Creates the tile and specifies the location in the grid.
        snakeHead = new Tile(5, 5);
        food = new Tile (10, 10);
        
        random = new Random();
        placeFood();
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
}