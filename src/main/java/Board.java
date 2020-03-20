import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Board implements ActionListener {
    private JFrame frame;
    Square[][] map = new Square[5][5];
    private Square btclicked;

    public Board (int[] input) {
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
        //counter for going through our file (which image show)
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                    map[i][j] = new Square(input[counter], i, j);
                    map[i][j].getButton().addActionListener(this);
                    panel.add(map[i][j].getButton());
                    counter++;
                }
                else map[i][j] = new Square(0, i, j);
                panel.add(map[i][j].getButton());
            }
        }
        frame.setContentPane(panel);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        //after each click check if only red frog exists
        int greenFrogs = 0;
        boolean redFrog = false;
        //2 loops to check which button was clicked
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ( e.getSource() == map[i][j].getButton() ) {
                    //check if we're making valid move -> valid length of jump and if we are jumping over a frog
                    if (btclicked.getDescription() > 3 && (!map[i][j].validMove(btclicked) ||
                            (map[(i + btclicked.getX()) / 2][(j + btclicked.getY()) / 2].getDescription() < 2 ||
                             (btclicked.getX() == i && btclicked.getY() == j) ) )){
                        map[btclicked.getX()][btclicked.getY()].
                            setSquare(btclicked.getDescription() - 2);
                        
                        btclicked.setDescription(0);
                    }
                    else {
                        btclicked = map[i][j].moveTo(btclicked);
                        //deletes frog over which we have jumped
                        map[(i + btclicked.getX()) / 2][(j + btclicked.getY()) / 2].setSquare(1);
                        map[btclicked.getX()][btclicked.getY()].setSquare(btclicked.getDescription());

                    }                   
                }
                
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j].getDescription() == 2 || map[i][j].getDescription() == 4) greenFrogs++;
                if (map[i][j].getDescription() == 3 || map[i][j].getDescription() == 5) redFrog = true;
            }
        }
        if (redFrog) {
            if (greenFrogs == 0) System.out.println("Congratulations!");
        }
        else System.out.println("You lost your frog, try again");
    }


    /*public static void main (String args[]) {
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

    }*/

    
}