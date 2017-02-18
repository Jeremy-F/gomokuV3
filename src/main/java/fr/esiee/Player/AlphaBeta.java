package fr.esiee.Player;

import fr.esiee.Board;
import fr.esiee.Box;
import javafx.scene.paint.Color;

/**
 * Created by jeremyfornarino on 18/02/2017.
 */
public class AlphaBeta extends IA {
    public AlphaBeta(String name, Color color) {
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

    @Override
    public Box findTheBestMove(Board board) {
        return null;
    }
}
