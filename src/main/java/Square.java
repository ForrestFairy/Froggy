import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Square {
    
    private ImageIcon img;
    private JButton bt;
    private int x;
    private int y;

//if water 0, if pad,1, if green frog 2, if red frog 3 
    public Square (int in, int xv, int yv) {

        if (in == 0) img = new ImageIcon("src/main/resources/Water.png", "0");
        if (in == 1) img = new ImageIcon("src/main/resources/LilyPad.png", "1");
        if (in == 2) img = new ImageIcon("src/main/resources/GreenFrog.png", "2");
        if (in == 3) img = new ImageIcon("src/main/resources/RedFrog.png", "3");
        if (in == 4) img = new ImageIcon("src/main/resources/GreenFrog2.png", "4");
        if (in == 5) img = new ImageIcon("src/main/resources/RedFrog2.png", "5");
        
        bt = new JButton(img);
        bt.setIcon(img);
        //bt.addActionListener(this);

        x = xv;
        y = yv;
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

    public void setX (int xv) {
        x = xv;
    }

    public void setY (int yv) {
        y = yv;
    }

    public int getDescription() {
        return Integer.parseInt(img.getDescription());
    }

    public void setDescription(String description) {
        img.setDescription(description);
    }

    public void setSquare (int in) {
        if (in == 0) img = new ImageIcon("src/main/resources/Water.png", "0");
        if (in == 1) img = new ImageIcon("src/main/resources/LilyPad.png", "1");
        if (in == 2) img = new ImageIcon("src/main/resources/GreenFrog.png", "2");
        if (in == 3) img = new ImageIcon("src/main/resources/RedFrog.png", "3");
        if (in == 4) img = new ImageIcon("src/main/resources/GreenFrog2.png", "4");
        if (in == 5) img = new ImageIcon("src/main/resources/RedFrog2.png", "5");

        bt.setIcon(img);
    }

    public Square moveTo (Square where) {
        //tests if not-water was clicked
        if( !(where.getDescription() < 2) && this.getDescription() == 1) {
            this.setSquare(where.getDescription());
            where.setSquare(1);
            
            return where;   
        }
        //if nothing was clicked before set btclicked to this bt
        where.setX(this.getX());
        where.setY(this.getY());
        where.setSquare(this.getDescription());
        // if (where.getDescription() == 2) where.setDescription("4");
        // if (where.getDescription() == 3) where.setDescription("5");
        return where;
    }


    /*
    //this should probably go to board
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
    }*/
}