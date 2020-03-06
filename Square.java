import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Square /*implements ActionListener*/ {
    
    private ImageIcon img;
    private JButton bt;


//if water 0, if pad,1, if green frog 2, if red frog 3 
    public Square (int i) {

        if (i == 0) img = new ImageIcon("Water.png");
        if (i == 1) img = new ImageIcon("LilyPad.png");
        if (i == 2) img = new ImageIcon("GreenFrog.png");
        if (i == 3) img = new ImageIcon("RedFrog.png");
        bt = new JButton(img);
        bt.setIcon(img);

    }

    public JButton getButton () {
        return bt;
    }

    public static void main (String[] args) {
        /*Square test0 = new Square(0);
        Square test1 = new Square(1);
        Square test2 = new Square(2);
        Square test3 = new Square(3);*/
    } 
}