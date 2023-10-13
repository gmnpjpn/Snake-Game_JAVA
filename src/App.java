import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int frameWidth = 600;
        int frameHeight = 600;
        ImageIcon img = new ImageIcon("assets/snakeIcon.png");

        JFrame frame = new JFrame("Snake"); // The frame represents ALL the window, including tittle bar.
        frame.setIconImage(img.getImage());
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null); // Open the window in the center of the screen.
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        snakeGame snakeGame = new snakeGame(frameWidth, frameHeight);
        frame.add(snakeGame);   // Jpanel inside of the frame
        frame.pack();   // Adjust the frame size (the tittle bar is inlcuded in the size, and we doesn't want that).
        
    }
}
