package fr.esiee.player.ia;

import fr.esiee.board.Board;
import fr.esiee.board.Box;
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
public class AlphaBeta extends MinMaxScorePlayer {
    public AlphaBeta(String name, Color color) {
        super(name, color);
    }
    @Override
    public Box findTheBestMove(Board board) {

        return null;
    }
}
