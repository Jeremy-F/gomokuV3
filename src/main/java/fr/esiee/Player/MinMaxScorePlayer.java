package fr.esiee.Player;

import fr.esiee.Board;
import fr.esiee.Box;
import javafx.scene.paint.Color;

import java.util.ArrayList;

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
public class MinMaxScorePlayer extends IA {

    public MinMaxScorePlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public int evaluate(Board board) {
        final int alignementSize = 2;
        Player otherPlayer = board.getOtherPlayer(this);
        final int getnumberOfAlignementsOfThis = board.getnumberOfAlignementsOf(this, alignementSize);
        final int getnumberOfAlignementsOfOther = board.getnumberOfAlignementsOf(otherPlayer, alignementSize);
        return getnumberOfAlignementsOfThis - getnumberOfAlignementsOfOther;
    }

    /**
     * presrequis : The game is not completed (we have empty box)
     *
     * @param board
     * @return
     */
    @Override
    public Box findTheBestMove(Board board) {

        int max = Integer.MIN_VALUE;
        int currentValue;
        ArrayList<Box> allEmptyBox = board.getAllEmptyBox();
        Box bestBox = allEmptyBox.get(0);
        for (Box box : allEmptyBox) {
            board.play(box.getLine(), box.getColumn());
            currentValue = min(board, Player.DEPTH);
            if (currentValue > max) {
                max = currentValue;
                bestBox = box;
            }

            board.cancelLastMove();
        }
        return bestBox;
    }

    private int min(Board board, int depth) {
        if (board.isFinished() || depth <= 0) {
            return board.scoreFor(this);
        }
        int min = Integer.MAX_VALUE;
        int currentValue;

        ArrayList<Box> allEmptyBox = board.getAllEmptyBox();

        for (Box box : allEmptyBox) {
            board.play(box.getLine(), box.getColumn());

            currentValue = max(board, depth - 1);
            if (currentValue < min) {
                min = currentValue;
            }

            board.cancelLastMove();
        }
        return min;
    }

    private int max(Board board, int depth) {
        if (board.isFinished() || depth <= 0) {
            return board.scoreFor(this);
        }
        int max = Integer.MIN_VALUE;
        int currentValue;

        ArrayList<Box> allEmptyBox = board.getAllEmptyBox();
        for (Box box : allEmptyBox) {
            board.play(box.getLine(), box.getColumn());

            currentValue = min(board, depth - 1);
            if (currentValue > max) {
                max = currentValue;
            }

            board.cancelLastMove();
        }
        return max;
    }
}
