package fr.esiee.Player;

import fr.esiee.Board;
import javafx.scene.paint.Color;

/**
 * Created by jeremyfornarino on 16/02/2017.
 */
public abstract class Player {
    public static final int DEPTH = 4;
    private String name;
    private Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }
    public abstract void play(Board board);
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\''+
                '}';
    }

    public abstract int evaluate(Board board);

    public Color getColor() {
        return color;
    }
}
