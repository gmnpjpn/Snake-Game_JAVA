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

    SnakeGame(int frameWidth, int frameHeight){
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        setPreferredSize(new Dimension(this.frameWidth, this.frameHeight));
        setBackground(Color.black);

        snakeHead = new Tile(5, 5);
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

        // Snake
        g.setColor(Color.white);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y *tileSize, tileSize, tileSize);


    }
}