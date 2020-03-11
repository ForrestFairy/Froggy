import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Square implements ActionListener {
    
    private ImageIcon img;
    private JButton bt;
    private int x;
    private int y;

//if water 0, if pad,1, if green frog 2, if red frog 3 
    public Square (int in, int x, int y) {

        if (in == 0) img = new ImageIcon("../../src/main/resources/Water.png", "0");
        if (in == 1) img = new ImageIcon("../../src/main/resources/LilyPad.png", "1");
        if (in == 2) img = new ImageIcon("../../src/main/resources/GreenFrog.png", "2");
        if (in == 3) img = new ImageIcon("../../src/main/resources/RedFrog.png", "3");
        if (in == 4) img = new ImageIcon("../../src/main/resources/GreenFrog2.png", "4");
        if (in == 5) img = new ImageIcon("../../src/main/resources/RedFrog2.png", "5");
        
        bt = new JButton(img);
        bt.setIcon(img);
        bt.addActionListener(this);

        x = x;
        y = y;
    }

    public JButton getButton() {
        return bt;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public void setSquare (int in) {
        if (in == 0) img = new ImageIcon("../../src/main/resources/Water.png", "0");
        if (in == 1) img = new ImageIcon("../../src/main/resources/LilyPad.png", "1");
        if (in == 2) img = new ImageIcon("../../src/main/resources/GreenFrog.png", "2");
        if (in == 3) img = new ImageIcon("../../src/main/resources/RedFrog.png", "3");
        if (in == 4) img = new ImageIcon("../../src/main/resources/GreenFrog2.png", "4");
        if (in == 5) img = new ImageIcon("../../src/main/resources/RedFrog2.png", "5");

        bt.setIcon(img);
    }

    public boolean moveTo (Square where) {
        if( !img.getDescription().equals("0") ) {
            where.setSquare(2);
            this.setSquare(1);
            return true;
        }
        return false;
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt) {
            //green to yellowGreen
            if (img.getDescription().equals("2") ) {
                this.setSquare(4);
            }
            //red to yellowRed
            if (img.getDescription().equals("3") ) {
                this.setSquare(5);
            }
        }
    }
}