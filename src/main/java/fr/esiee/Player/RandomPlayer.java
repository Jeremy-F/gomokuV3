package fr.esiee.Player;

import fr.esiee.Board;
import fr.esiee.Box;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jeremyfornarino on 17/02/2017.
 */
public class RandomPlayer extends IA{
    public RandomPlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public int evaluate(Board board) {
        return 0;
    }

    @Override
    public Box findTheBestMove(Board board) {
        ArrayList<Box> allEmptyBox = board.getAllEmptyBox();
        return allEmptyBox.get(new Random().nextInt(allEmptyBox.size()));
    }
}
