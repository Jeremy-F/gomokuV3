package fr.esiee;

import fr.esiee.Player.Player;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by jeremyfornarino on 16/02/2017.
 */
public class Box implements Cloneable{
    private int line;
    private int column;
    private Player owner;

    public Box(int line, int column, Player owner) {
        this.line = line;
        this.column = column;
        this.owner = owner;
    }
    public boolean hasOwner(){
        return this.owner != null;
    }
    public int getLine() {
        return line;
    }
    public int getColumn() {
        return column;
    }

    public Player getOwner() {
        return owner;
    }

    public Box(int line, int column) {
        this(line,column,null);
    }
    @Override
    public String toString() {
        return "Box{ " +
                " adrr=" + super.toString().substring(13, super.toString().length()) +
                ", line=" + line +
                ", column=" + column +
                ", owner=" + owner +
                '}';
    }
    public void setLine(int line) {
        this.line = line;
    }

    public Box setOwner(Player owner) {
        this.owner = owner;
        return this;
    }
    /**
     * Create a circle for the box
     * @return A Node wich contain the representation of the box
     */
    public Node toNode(){

        Circle circle = new Circle();

        circle.setRadius(40);

        if(hasOwner()){
            circle.setStroke(Color.BLACK); // black border
            circle.setFill(this.owner.getColor());  // player color for the circle color
        }else{
            circle.setFill(Color.WHITE); // By default, the circle is white
        }


        return circle;//*/
    }
}
