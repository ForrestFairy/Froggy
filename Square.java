import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Square implements ActionListener {
    
    private ImageIcon img;
    private JButton bt;
    private int x;
    private int y;

//if water 0, if pad,1, if green frog 2, if red frog 3 
    public Square (int i, int j, int k) {

        if (i == 0) img = new ImageIcon("Water.png", "0");
        if (i == 1) img = new ImageIcon("LilyPad.png", "1");
        if (i == 2) img = new ImageIcon("GreenFrog.png", "2");
        if (i == 3) img = new ImageIcon("RedFrog.png", "3");
        
        bt = new JButton(img);
        bt.setIcon(img);
        bt.addActionListener(this);

        x = j;
        y = k;
    }

    public JButton getButton () {
        return bt;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt) {
            //green to yellowGreen
            if (img.getDescription().equals("2") ) {
                img = new ImageIcon("GreenFrog2.png", "4");
                bt.setIcon(img);
            }
            //red to yellowRed
            if (img.getDescription().equals("3") ) {
                img = new ImageIcon("RedFrog2.png", "5");
                bt.setIcon(img);
            }
        }
    }
}