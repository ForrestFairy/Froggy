import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.*;

/**
 * Menu class
 * menu for game called Froggy
 * in JTextField player can choose which level to play form level 1 to 40
 * @author Szymon Bogusz
 */
public class Menu implements KeyListener {

    private JFrame frame;
    private JTextField chooseLevel;
    private int[][] level = new int[40][13];
    private Board game;
/**
 * Menu creator
 * opens small frame
 * all levels are loaded to level 2d array form "levels.txt"
 * @throws FileNotFoundException
 */
    public Menu() throws FileNotFoundException {
        //Initialize menu window
        frame = new JFrame();
        frame.setTitle("Froggy");
        frame.setSize(200, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout menu = new GridLayout(2, 1);
        JPanel panel = new JPanel();
        panel.setLayout(menu);
        frame.addKeyListener(this);

        //Initialize menu
        JTextArea welcomeMessage = new JTextArea("Welcome to Hoppers! Please select level below");
        welcomeMessage.setEditable(false);
        welcomeMessage.setLineWrap(true);
        welcomeMessage.setWrapStyleWord(true);
        panel.add(welcomeMessage);
        
        chooseLevel = new JTextField("Which level do you wanna try?");
        chooseLevel.setEditable(true);
        chooseLevel.addKeyListener(this);
        panel.add(chooseLevel);

        frame.setContentPane(panel);
        frame.setVisible(true);

        //get levels' maps
        try {
            File file = new File("src/main/resources/levels.txt");
            Scanner scan = new Scanner(file);
            
            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 13; j++) {
                    level[i][j] = scan.nextInt();
                }
            }
            scan.close();
        }
        catch (FileSystemNotFoundException e) {
            e.printStackTrace();
        }
        
    }

/**
 * waits until enter is clicked
 * if enter is clicked this method checks what is written in JTextField
 * if not number from 1 to 40 then asks in JTextField to put number
 * if number from 1 to 40 is put then opens Board class with suitable level and closes this frame
 */
    public void keyPressed(KeyEvent e) {
        //when enter is clicked in textArea check if valid level was entered
        //enter code is 10
        if (e.getKeyCode() == 10) {
            int whichLevel = Integer.parseInt(chooseLevel.getText());
            if (whichLevel < 1 || whichLevel > 40) chooseLevel.setText("Put number between 1 and 40");
            else {
                game = new Board(level[whichLevel - 1]);
                frame.setVisible(false);
            }
        }

    }

    public void keyReleased (KeyEvent e) {

    }

    public void keyTyped (KeyEvent e) {

    }

    public static void main (String args[]) throws FileNotFoundException {
        Menu start = new Menu();
    }
}