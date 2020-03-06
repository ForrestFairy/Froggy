import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Board {
    private JFrame frame;
    Square[][] map = new Square[5][5];
    
    public Board () {
        //setting frame ready
        frame = new JFrame();
        frame.setTitle("Froggy");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridLayout mainLayout = new GridLayout(5, 5);
        JPanel panel = new JPanel();
        panel.setLayout(mainLayout);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new Square(0);
                panel.add(map[i][j].getButton());
            } 
        }
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public static void main (String args[]) {
        Board game = new Board();
    }
}