package fr.esiee.player.ia;

import fr.esiee.board.Board;
import fr.esiee.board.Box;
import fr.esiee.player.Player;
import javafx.scene.paint.Color;

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

        long duration = System.currentTimeMillis() - initTime;
        board.play(bestMove.getLine(), bestMove.getColumn(), duration);
    }

    public abstract Box findTheBestMove(Board board);
}
