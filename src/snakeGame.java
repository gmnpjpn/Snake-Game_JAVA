import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class snakeGame extends JPanel{
    int frameWidth;
    int frameHeight;

    snakeGame(int frameWidth, int frameHeight){
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        setPreferredSize(new Dimension(this.frameWidth, this.frameHeight));
        setBackground(Color.black);
        
    }
    
}
