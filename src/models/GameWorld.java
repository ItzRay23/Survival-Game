package models;

import java.sql.Time;
import java.util.HashMap;
import java.util.Random;

public class GameWorld implements IGameWorld {
    private static final int MAX_CHANCE = 101;

    private int width;
    private int height;
    private char[][] worldGrid;
    private char[] tiles = {'*', '~', '?', '!'};

    public char getTileDirection(Move direction) {
        if (direction.getDX() < 0 || direction.getDX() >= width || direction.getDY() < 0 || direction.getDY() >= height) {
            return ' ';
        }
        return worldGrid[direction.getDX()][direction.getDY()];
    }

    public char getTile(GamePosition pos) {
        return worldGrid[pos.getRow()][pos.getColumn()];
    }

    public HashMap<String, Character> scanSurrounding(GamePosition pos){
        HashMap<String, Character> surrounding = new HashMap<>();
        
        surrounding.put("N", getTileDirection(new Move(Move.Direction.N)));
        surrounding.put("S", getTileDirection(new Move(Move.Direction.S)));
        surrounding.put("E", getTileDirection(new Move(Move.Direction.E)));
        surrounding.put("W", getTileDirection(new Move(Move.Direction.W)));
        surrounding.put("NE", getTileDirection(new Move(Move.Direction.NE)));
        surrounding.put("NW", getTileDirection(new Move(Move.Direction.NW)));
        surrounding.put("SE", getTileDirection(new Move(Move.Direction.SE)));
        surrounding.put("SW", getTileDirection(new Move(Move.Direction.SW)));

        return surrounding;
    }

    public GameWorld() {
        this.width = 10;
        this.height = 10;
        worldGrid = new char[width][height];
    }

    public GameWorld(int width, int height) {
        worldGrid = new char[height][width];
    }

    public void generateWorld(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tileRandomizer(new GamePosition(i, j));
            }
        }
    }

    private void tileRandomizer(GamePosition pos) {
        Random rand = new Random();
        int randomIndex = 0;
        if (rand.nextInt(MAX_CHANCE) > 75) {
            randomIndex = 1;
        }
        if (rand.nextInt(MAX_CHANCE) > 85) {
            randomIndex = 2;
        }
        if (rand.nextInt(MAX_CHANCE) > 95) {
            randomIndex = 3;
        }
        worldGrid[pos.getRow()][pos.getColumn()] = tiles[randomIndex];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= width; i++) {
            if (i == 0) {
                sb.append("  ");
            }
            sb.append("___");
        }

        sb.append("\n");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j == 0) {
                    sb.append(" | ").append(worldGrid[i][j]).append(" ");
                }
                sb.append(" ").append(worldGrid[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
