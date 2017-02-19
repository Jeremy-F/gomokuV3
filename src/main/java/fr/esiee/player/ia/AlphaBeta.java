package fr.esiee.player.ia;

import fr.esiee.board.Board;
import fr.esiee.board.Box;
import fr.esiee.core.Movement;
import fr.esiee.player.Player;
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
public class AlphaBeta extends MinMaxScorePlayer {
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
        int bestScore = Integer.MIN_VALUE;
        Box finalBox = null;
        ArrayList<Box> allEmptyBox = board.getAllEmptyBox();
        for (Box box : allEmptyBox) {
            board.play(box.getLine(), box.getColumn());

            int alphaBeta = this.alphaBeta(board, Player.DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (alphaBeta > bestScore) {
                bestScore = alphaBeta;
                finalBox = box;
            }
            board.cancelLastMove();
        }
        System.out.println(finalBox);
        return finalBox;
    }

    private int alphaBeta(Board board, int depth, int alpha, int beta) {
        if (board.isFinished() || depth <= 0) {
            return board.scoreFor(this);
        }
        Movement lastMovement = board.getLastMove();
        Player lastPlayer = board.getPlayer(lastMovement.getPlayerIndex());
        ArrayList<Box> allEmptyBox = board.getAllEmptyBox();
        if(!lastPlayer.equals(this)){
            for(Box emptyBox : allEmptyBox){
                beta = Integer.min(beta, this.alphaBeta(board, depth-1, alpha, beta));
                if(alpha >= beta){
                    return alpha;
                }
            }
            return beta;
        }for(Box emptyBox : allEmptyBox){
            alpha = Integer.max(alpha, this.alphaBeta(board, depth-1, alpha, beta));
            if(alpha >= beta){
                return beta;
            }
        }
        return alpha;
    }
}
