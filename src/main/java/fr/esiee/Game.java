package fr.esiee;

import fr.esiee.player.*;
import fr.esiee.board.Board;
import fr.esiee.player.ia.MinMaxScorePlayer;
import fr.esiee.player.ia.RandomPlayer;
import javafx.scene.layout.GridPane;
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
public class Game{


    private Board board;
    private GridPane gridPane;


    public static void main(String[] args) {
        Player alexandre = new RandomPlayer("Alexandre", Color.BLUE);
        Player jeremy = new MinMaxScorePlayer("Jérémy", Color.RED);
        Board board = new Board(null, 4, 4, alexandre, jeremy);
        board.launch();
    }
}
