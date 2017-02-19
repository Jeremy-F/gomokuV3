package fr.esiee.player;

import fr.esiee.board.Board;
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
public abstract class Player {
    public static final int DEPTH = 4;
    private String name;
    private Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }
    public abstract void play(Board board);
    @Override
    public String toString() {
        return "player{" +
                "name='" + name + '\''+
                '}';
    }

    public abstract int evaluate(Board board);

    public Color getColor() {
        return color;
    }
}
