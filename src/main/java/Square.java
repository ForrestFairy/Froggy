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

    public void setDescription(int description) {
        img.setDescription(Integer.toString(description));
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

    public boolean validMove(Square where) {
        //if destination leaf is occupied then its invalid move
        if (this.getDescription() > 1) return false;
        //break down to all valid moves
        //going right
        if (where.getX() - this.getX() == 4 && where.getY() == this.getY()) return true;
        //going left
        if (this.getX() - where.getX() == 4 && where.getY() == this.getY()) return true;
        //going down
        if (where.getY() - this.getY() == 4 && where.getX() == this.getX()) return true;
        //going up
        if (this.getY() - where.getY() == 4 && where.getX() == this.getX()) return true;
        //up right
        if (this.getY() - where.getY() == 2 && where.getX() - this.getX() == 2) return true;
        //up left
        if (this.getY() - where.getY() == 2 && this.getX() - where.getX() == 2) return true;
        //down right
        if (where.getY() - this.getY() == 2 && where.getX() - this.getX() == 2) return true;
        //down left
        if (where.getY() - this.getY() == 2 && this.getX() - where.getX() == 2) return true;
        //else return false
        return false;
    }

    public Square moveTo (Square where) {
        //valid move
        if (validMove(where)) {
            //tests if not-water was clicked
            if( where.getDescription() > 1 && this.getDescription() == 1) {
                this.setSquare(where.getDescription() - 2);
                where.setSquare(1);
                
                return where;   
            }
        }
        //if nothing was clicked before set btclicked to this bt
        where.setX(this.getX());
        where.setY(this.getY());
        where.setSquare(this.getDescription());
        if (where.getDescription() == 2 || where.getDescription() == 3) {
            where.setSquare(where.getDescription() + 2);
        }
        
        return where;
    }
    
}