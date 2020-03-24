import javax.swing.*;
/**
 * Square class is 1 of 25 tiles in Froggy game map
 * Each tile is a JButton, displaying an image which has also a description.
 * Description is not displayed but is used to analyze what is shown on a tile.
 * Square also holds its own x and y coordinate which can be from 0 to 4
 * @author Szymon Bogusz
 */
public class Square {
    
    private ImageIcon img;
    private JButton bt;
    private int x;
    private int y;
/**
 * Square constructor
 * @param in image id from 0 to 5 which should be displayed
 * 0 creates water tile
 * 1 creates pad tile
 * 2 creates greenFrog tile
 * 3 creates redFrog tile
 * 4 creates yellowGreenFrog tile
 * 5 creates yellowRedFrog tile
 * @param xv x coordinate on 5x5 map
 * @param yv y coordinate on 5x5 map
 */
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

        x = xv;
        y = yv;
    }
/**
 * getButton method 
 * @return JButton from Square
 */
    public JButton getButton() {
        return bt;
    }
/**
 * getX method
 * @return int which is x coordinate of this Square
 */
    public int getX () {
        return x;
    }
/**
 * getY method
 * @return int which is y coordinate of this Square
 */
    public int getY () {
        return y;
    }
/**
 * setX method
 * used to change x coordinate of this Square
 * @param xv not negative integer value lower than 5
 */
    public void setX (int xv) {
        x = xv;
    }
/**
 * setY method
 * used to change y coordinate of this Square
 * @param yv not negative integer value lower than 5
 */
    public void setY (int yv) {
        y = yv;
    }
/**
 * getDescription method
 * @return integer from 0 to 5 which is an id of image shown
 */
    public int getDescription() {
        return Integer.parseInt(img.getDescription());
    }
/**
 * setDescription method
 * used to change Square's image description
 * @param description not negative integer lower than 6
 */
    public void setDescription(int description) {
        img.setDescription(Integer.toString(description));
    }
/**
 * setSquare method
 * used to change image and its description of a Square
 * @param in not negative integer lower than 6 which indicates what will be displayed
 */
    public void setSquare (int in) {
        if (in == 0) img = new ImageIcon("src/main/resources/Water.png", "0");
        if (in == 1) img = new ImageIcon("src/main/resources/LilyPad.png", "1");
        if (in == 2) img = new ImageIcon("src/main/resources/GreenFrog.png", "2");
        if (in == 3) img = new ImageIcon("src/main/resources/RedFrog.png", "3");
        if (in == 4) img = new ImageIcon("src/main/resources/GreenFrog2.png", "4");
        if (in == 5) img = new ImageIcon("src/main/resources/RedFrog2.png", "5");

        bt.setIcon(img);
    }
/**
 * validMove method
 * used to check if frog is not trying to jump on water or on another frog
 * checks if jump is of valid length and direction
 * @param where Square from which frog is trying to jump
 * @return jump is valid then true
 * else return false
 */
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
/**
 * moveTo method
 * takes JButton where
 * if "where" is empty (no frog is in it) moveTo method changes where
 * to currently clicked Square
 * if "where" contains different Square with frog moveTo method first checks
 * if wanted jump to perform is valid, if so change this Square to frog from "where"
 * if not unclick the "where" Square
 * @param where Square from which we are jumping to this Square
 * @return changed where Square
 */
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