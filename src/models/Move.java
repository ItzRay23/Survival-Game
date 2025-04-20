package models;

public class Move {
    
    private int dx = 0;
    private int dy = 0;
    
    public enum Direction {Q, W, E, A, D, Z, S, C}

    /**
     * Sets the change in x-coordinate based on the direction.
     * @param direction The direction to move in. Enum value of Direction in terms of a compass (N, S, E, W)
     * @return The change in x-coordinate (dx) based on the direction.
     */
    public static int setDX(Direction direction) {
        switch (direction) {
            case D:
                return 1;
            case A:
                return -1;
            case E:
                return 1;
            case Q:
                return -1;
            case C:
                return 1;
            case Z:
                return -1;
            default:
                return 0;
        }
    }

    /**
     * Sets the change in y-coordinate based on the direction.
     * @param direction The direction to move in. Enum value of Direction {N, S, E, W, NE, NW, SE, SW}
     * @return The change in y-coordinate (dy) based on the direction.
     */
    public static int setDY(Direction direction) {
        switch (direction) {
            case W:
                return -1;
            case S:
                return 1;
            case E:
                return -1;
            case Q:
                return -1;
            case C:
                return 1;
            case Z:
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
