package fr.esiee;
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
public class Movement {
    private int line;
    private int column;
    private int playerIndex;

    public Movement(int line, int column, int playerIndex) {
        this.line = line;
        this.column = column;
        this.playerIndex = playerIndex;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "line=" + line +
                ", column=" + column +
                ", playerIndex=" + playerIndex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movement movement = (Movement) o;

        if (line != movement.line) return false;
        if (column != movement.column) return false;
        return playerIndex == movement.playerIndex;
    }

    @Override
    public int hashCode() {
        int result = line;
        result = 31 * result + column;
        result = 31 * result + playerIndex;
        return result;
    }
}
