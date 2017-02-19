package fr.esiee.core;
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
    private long executionTime;

    public Movement(int line, int column, int playerIndex) {
        this(line,column,playerIndex,-1);
    }

    public Movement(int line, int column, int playerIndex, long executionTime) {
        this.line = line;
        this.column = column;
        this.playerIndex = playerIndex;
        this.executionTime = executionTime;
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
                ", executionTime=" + executionTime +
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
