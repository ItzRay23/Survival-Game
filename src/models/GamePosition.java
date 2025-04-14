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

    public static boolean isInBounds(int maxRow, int maxCol) {
        return row >= -(maxRow / 2) + 1 && row < (maxRow / 2) - 1 && col >= -(maxCol / 2) + 1 && col < (maxCol / 2) - 1;
    }

    @Override
    public String toString() {
        return "Player Position: (" + row + ", " + col + ")";
    }
}
