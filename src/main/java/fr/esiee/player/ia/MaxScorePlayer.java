package fr.esiee.player.ia;

import fr.esiee.board.Board;
import fr.esiee.board.Box;
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
public class MaxScorePlayer extends IA {

    public MaxScorePlayer(String name, Color color) {
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
    //*
    public static int cpt = 0;
    @Override
    public Box findTheBestMove(Board board) {

        Box finalBox = null;
        int score = Integer.MIN_VALUE;
        ArrayList<Box> allEmptyBox = board.getAllEmptyBox();
        for(Box emptyBox : allEmptyBox){
            board.play(emptyBox.getLine(), emptyBox.getColumn());
            //*
            int myScore = score(board, Player.DEPTH);
            //score = board.scoreFor(this);
            if(score < myScore){
                score = myScore;
                finalBox = emptyBox;
            }//*/


            board.cancelLastMove();
        }

        return finalBox;
    }
    public int score(Board board, int depth){
        if(board.isFinished() || depth <= 0){
            int currentScore = board.scoreFor(this);
            //board.cancelLastMove();
            return currentScore;
        }
        int score = Integer.MIN_VALUE;
        ArrayList<Box> allEmptyBox = board.getAllEmptyBox();
        for(Box emptyBox : allEmptyBox){
            board.play(emptyBox.getLine(), emptyBox.getColumn());
            //
            int myScore = score(board, depth-1);
            if(score < myScore){
                score = myScore;
            }
            //if(!board.isFinished()){
                board.cancelLastMove();
            //}

        }
        return score;
    }
    //*/

    //*/

}
