package fr.esiee.Player;

import fr.esiee.Board;
import javafx.scene.paint.Color;

/**
 * Created by jeremyfornarino on 18/02/2017.
 */
public class Person extends Player{
    public Person(String name, Color color) {
        super(name, color);
    }

    @Override
    public void play(Board board) {

    }

    @Override
    public int evaluate(Board board) {
        return 0;
    }
}
