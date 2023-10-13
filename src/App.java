import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int frameWidth = 600;
        int frameHeight = 600;

        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null); // Open the window in the center of the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    }
}
