package fr.esiee.Player;

import fr.esiee.Board;
import fr.esiee.Box;
import javafx.scene.paint.Color;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;

/**
 * Created by jeremyfornarino on 17/02/2017.
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
            System.out.println(emptyBox);
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
            System.out.println(this);
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
        System.out.println("New score : "+score);
        return score;
    }
    //*/

    //*/

}
