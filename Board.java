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
        frame.setSize(750, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridLayout mainLayout = new GridLayout(5, 5);
        JPanel panel = new JPanel();
        panel.setLayout(mainLayout);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new Square(0, i, j);

                panel.add(map[i][j].getButton());
            }
        }
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public static void main (String args[]) {
        Board game = new Board();
        //Task 2 row 1
        game.map[0][0].setSquare(1);
        game.map[0][2].setSquare(1);
        game.map[0][4].setSquare(1);
        //row 2
        game.map[1][1].setSquare(2);
        game.map[1][3].setSquare(2);
        //row 3
        game.map[2][0].setSquare(1);
        game.map[2][2].setSquare(2);
        game.map[2][4].setSquare(1);
        //row 4
        game.map[3][1].setSquare(1);
        game.map[3][3].setSquare(1);
        //row 5
        game.map[4][0].setSquare(2);
        game.map[4][2].setSquare(3);
        game.map[4][4].setSquare(2);
    }
}