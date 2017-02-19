package fr.esiee.player.ia;

import fr.esiee.board.Board;
import fr.esiee.board.Box;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;
/**
 *****************************************************
 * ,----.     E3T - Esiee Paris      ,--.            *
 * '  .-./    ,---. ,--,--,--. ,---. |  |,-.,--.,--. *
 * |  | .---.| .-. ||        || .-. ||     /|  ||  | *
 * '  '--'  |' '-' '|  |  |  |' '-' '|  \  \'  ''  ' *
 * `------'  `---' `--`--`--' `---' `--'`--'`------' *
 *    Alexandre Causse            Jérémy Fornarino   *
 *****************************************************
 * @author Alexandre Causse & Jérémy Fornarino   [E3T]
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
