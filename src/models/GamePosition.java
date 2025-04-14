package models;

public class GamePosition {

    private static int row;
    private static int col;

    public GamePosition(int r, int c) {
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    public boolean isInBounds(int maxRow, int maxCol) {
        return row >= 0 && row < maxRow && col >= 0 && col < maxCol;
    }

    @Override
    public String toString() {
        return "(" + getRow() + ", " + getColumn() + ")";
    }
}
