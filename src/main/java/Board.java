import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Board implements ActionListener {
    private JFrame frame;
    Square[][] map = new Square[5][5];
    public Square btclicked;

    public Board () {
        //setting frame ready
        frame = new JFrame();
        frame.setTitle("Froggy");
        frame.setSize(750, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridLayout mainLayout = new GridLayout(5, 5);
        JPanel panel = new JPanel();
        panel.setLayout(mainLayout);
        //initialize to water
        btclicked = new Square(0, 1, 1);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new Square(0, i, j);
                map[i][j].getButton().addActionListener(this);
                panel.add(map[i][j].getButton());
            }
        }
        frame.setContentPane(panel);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        
        //2 loops to check which button was clicked
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ( e.getSource() == map[i][j].getButton() ) {
                    btclicked = map[i][j].moveTo(btclicked);
                    if (btclicked.getDescription() == 1) {
                        map[btclicked.getX()][btclicked.getY()].setSquare(1);
                    }

                    
                    /*
                    //tests and printing of where we currently click
                    System.out.println(map[i][j].getX() + " " + map[i][j].getY() + "  "+
                        btclicked.getX() + " " + btclicked.getY());
                    System.out.println(btclicked.getDescription());*/
                }
            }
        }
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