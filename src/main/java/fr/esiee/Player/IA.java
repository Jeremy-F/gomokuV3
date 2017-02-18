package fr.esiee.Player;

import fr.esiee.Board;
import fr.esiee.Box;
import javafx.scene.paint.Color;

/**
 * Created by jeremyfornarino on 17/02/2017.
 */
public abstract class IA extends Player {

    public void simulateOneTurnOn(Board board, int line, int column){
        board.play(line, column);
    }

    public IA(String name, Color color) {
        super(name, color);
    }
    @Override
    public void play(Board board) {
        long initTime = System.currentTimeMillis();

        Box bestMove = this.findTheBestMove(board);
        board.play(bestMove.getLine(), bestMove.getColumn());


        long duration = System.currentTimeMillis() - initTime;
        System.out.println("Coup : "+board.getNumberOfMove()+" | Duree : "+ (duration * Math.pow(10,-3)) );
    }

    public abstract Box findTheBestMove(Board board);
}
