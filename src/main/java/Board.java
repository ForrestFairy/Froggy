import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

/**
 * Board class
 * Contains 1050x750? frame
 * from which 750x750 should go for 2d Square array called map
 * Board also has JButton btclicked to store currently clicked button
 * Current level is stored in integer array called level
 * At the top of frame there are giveUp clickable button if player don't know how to beat level
 * so want to come back to menu
 * and tryAgain clickable button if player want to try completing same level again
 * @author Szymon Bogusz
 */
public class Board implements ActionListener {
    private JFrame frame;
    Square[][] map = new Square[5][5];
    private Square btclicked;
    private int[] level;
    private int whichLevel;
    private JButton whichLevelField;
    private JButton giveUp;
    private JButton tryAgain;
/**
 * Board constructor
 * This method returns frame containing
 * giveUp and tryAgain buttons at the top
 * and 5x5 Square map in the center
 *  
 * Current level is loaded from input
 * @param input containing level to load
 */
    public Board(int[] input, int which) {
        level = input;
        whichLevel = which;
        // setting frame ready
        frame = new JFrame();
        frame.setTitle("Froggy");
        frame.setSize(1050, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        BorderLayout bigLayout = new BorderLayout();
        mainPanel.setLayout(bigLayout);

        GridLayout mainLayout = new GridLayout(5, 5);
        JPanel panel = new JPanel();
        panel.setLayout(mainLayout);
        // initialize btclicked to water
        btclicked = new Square(0, 1, 1);
        // counter for going through our file (which image show)
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                    map[i][j] = new Square(input[counter], i, j);
                    map[i][j].getButton().addActionListener(this);
                    panel.add(map[i][j].getButton());
                    counter++;
                } else
                    map[i][j] = new Square(0, i, j);
                panel.add(map[i][j].getButton());
            }
        }
        //setup panel for giveUp and tryAgain buttons and for textfield displaying which level
        // are we playing right now
        JPanel btPanel = new JPanel(new GridLayout(1, 3));
        giveUp = new JButton("Give up?");
        giveUp.addActionListener(this);
        tryAgain = new JButton("Try again?");
        tryAgain.addActionListener(this);
        whichLevelField = new JButton("Level " + whichLevel);
        whichLevelField.addActionListener(this);
        btPanel.add(giveUp);
        btPanel.add(whichLevelField);
        btPanel.add(tryAgain);

        mainPanel.add("Center", panel);
        mainPanel.add("North", btPanel);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
/**
 * endGame method
 * after each click it check if there is redFrog
 * if not print message to terminal and start level again
 * if there is red frog checks if there is no greenFrog
 * if yes then print message to terminal and open new Menu class
 * if there is at least 1 greenFrog - do nothing
 */
    public void endGame () {
        int greenFrogs = 0;
        boolean redFrog = false;
        // 2 loops to check which button was clicked
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j].getDescription() == 2 || map[i][j].getDescription() == 4)
                    greenFrogs++;
                if (map[i][j].getDescription() == 3 || map[i][j].getDescription() == 5)
                    redFrog = true;
            }
        }
        if (redFrog) {
            if (greenFrogs == 0) {
                //change middle button to win message 
                whichLevelField.setText("Congratulations!");
            }
        } else {
            //change middle button to lost message 
            whichLevelField.setText("You lost your frog, try again");
        }
    }

/**
 * if anything is clicked check which button was clicked
 * if btclicked holds Square with frog check if it can go to currently clicked Square
 * i.e. checks if jump will be performed over another frog
 * if move is valid perform it and delete frog over which jump was performed
 * else if no Square was selected  select the one clicked
 * if some Square was selected and move is not valid unselect it
 * 
 * after move chceck if the level is done
 * 
 * if tryAgain button was clicked - load same level again
 * if giveUp button was clicked - load new menu 
 */
    public void actionPerformed(ActionEvent e) {
        // 2 loops to check which button was clicked
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (e.getSource() == map[i][j].getButton()) {
                    // check if we're making valid move -> valid length of jump and if we are
                    // jumping over a frog
                    if (btclicked.getDescription() > 3 && (!map[i][j].validMove(btclicked)
                            || (map[(i + btclicked.getX()) / 2][(j + btclicked.getY()) / 2].getDescription() < 2
                                    || (btclicked.getX() == i && btclicked.getY() == j)))) {
                        map[btclicked.getX()][btclicked.getY()].setSquare(btclicked.getDescription() - 2);

                        btclicked.setDescription(0);
                    } else {
                        btclicked = map[i][j].moveTo(btclicked);
                        // deletes frog over which we have jumped
                        map[(i + btclicked.getX()) / 2][(j + btclicked.getY()) / 2].setSquare(1);
                        map[btclicked.getX()][btclicked.getY()].setSquare(btclicked.getDescription());
                    }
                }
            }
        }

        //check if game is done
        endGame();

        //if giveUp button was clicked go to menu
        if (e.getSource() == giveUp) {
            try {
                Menu menu = new Menu();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            //close this window
            frame.setVisible(false);
        }

        if (e.getSource() == tryAgain) {
            //open new window
            new Board(level, whichLevel);
            //close this window
            frame.setVisible(false);
        }
        if (e.getSource() == whichLevelField) {
            if ( !whichLevelField.getText().equals("Level " + whichLevel)) {
                try {
                    Menu menu = new Menu();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                //close this window
                frame.setVisible(false);
            }
        }
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