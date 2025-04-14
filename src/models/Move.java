package models;

public class Move {
    
    private int dx = 0;
    private int dy = 0;
    
    enum Direction {
        N, S, E, W, NE, NW, SE, SW
    }

    /**
     * Sets the change in x-coordinate based on the direction.
     * @param direction The direction to move in. Enum value of Direction in terms of a compass (N, S, E, W)
     * @return The change in x-coordinate (dx) based on the direction.
     */
    public static int setDX(Direction direction) {
        switch (direction) {
            case E:
                return 1;
            case W:
                return -1;
            case NE:
                return 1;
            case NW:
                return -1;
            case SE:
                return 1;
            case SW:
                return -1;
            default:
                return 0;
        }
    }

    /**
     * Sets the change in y-coordinate based on the direction.
     * @param direction The direction to move in. Enum value of Direction in terms of a compass (N, S, E, W)
     * @return The change in y-coordinate (dy) based on the direction.
     */
    public static int setDY(Direction direction) {
        switch (direction) {
            case N:
                return -1;
            case S:
                return 1;
            case NE:
                return -1;
            case NW:
                return -1;
            case SE:
                return 1;
            case SW:
                return 1;
            default:
                return 0;
        }
    }

    public Move(Direction direction) {
        this.dx = setDX(direction);
        this.dy = setDY(direction);
    }

    public int getDX() {
        return dx;
    }
    public int getDY() {
        return dy;
    }
}
