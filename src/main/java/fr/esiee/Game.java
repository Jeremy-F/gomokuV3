package fr.esiee;

import fr.esiee.player.*;
import fr.esiee.board.Board;
import fr.esiee.player.ia.MinMaxScorePlayer;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.*;

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


    public static void main(String[] args){
        String pathname = Game.class.getResource("").toString() + "MinMaxVSMinMax-Dept4-5x5.csv";
        pathname = pathname.substring(5, pathname.length());
        File file = new File(pathname);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileWriter fw = new FileWriter(pathname, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println("Player1, Player2,nbMovementPlayed,depth,winnerIndex,executionTimePlayer1,executionTimePlayer2");
            for (int i = 0; i < 1; i++) {
                System.out.println("Jeux : "+i);
                Player alexandre = new MinMaxScorePlayer("Alexandre", Color.BLUE);
                Player jeremy = new MinMaxScorePlayer("Jérémy", Color.RED);
                Board board = new Board(null, 5, 5, alexandre, jeremy);
                board.launch();
                out.println(board.toCSVLine());
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
