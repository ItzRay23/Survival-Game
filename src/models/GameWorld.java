package models;

import java.util.HashMap;
import java.util.Random;

public class GameWorld implements IGameWorld {
    private static final int MAX_CHANCE = 101;

    private int width;
    private int height;
    private char[][] worldGrid;
    private char[][] displayGrid;
    private char[] tiles = {'*', '~', '?', '!'};

    private boolean isDirectionInBounds(GamePosition pos, Move dir) {
        return !(pos.getRow() + dir.getDY() >= 0 && pos.getRow() + dir.getDY() < height) &&
               (pos.getColumn() + dir.getDX() >= 0 && pos.getColumn() + dir.getDX() < width);
    }

    private char getTileDirection(GamePosition pos, Move direction) {
        if (isDirectionInBounds(pos, direction)) {
            return ' ';
        }
        return worldGrid[pos.getRow() + direction.getDY()][pos.getColumn() + direction.getDX()];
    }

    public char getTile(GamePosition pos) {
        return worldGrid[pos.getRow()][pos.getColumn()];
    }

    public HashMap<String, Character> scanSurrounding(GamePosition pos){
        HashMap<String, Character> surrounding = new HashMap<>();
        
        surrounding.put("N", getTileDirection(pos, new Move(Move.Direction.N)));
        surrounding.put("S", getTileDirection(pos, new Move(Move.Direction.S)));
        surrounding.put("E", getTileDirection(pos, new Move(Move.Direction.E)));
        surrounding.put("W", getTileDirection(pos, new Move(Move.Direction.W)));
        surrounding.put("NE", getTileDirection(pos, new Move(Move.Direction.NE)));
        surrounding.put("NW", getTileDirection(pos, new Move(Move.Direction.NW)));
        surrounding.put("SE", getTileDirection(pos, new Move(Move.Direction.SE)));
        surrounding.put("SW", getTileDirection(pos, new Move(Move.Direction.SW)));

        return surrounding;
    }

    public void revealSurrounding(GamePosition pos) {
        HashMap<String, Character> surrounding = scanSurrounding(pos);
        for (String direction : surrounding.keySet()) {
            char tile = surrounding.get(direction);
            System.out.println("Tile in " + direction + " direction: \'" + tile + "\'");
        }
    }

    public GameWorld() {
        width = 10;
        height = 10;
        worldGrid = new char[height][width];
    }

    public GameWorld(int height, int width) {
        this.width = width;
        this.height = height;
        worldGrid = new char[height][width];
    }

    public void generateWorld(int height, int width) {
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
        for (int i = 0; i < width; i++) {
            if (i == 0) {
                sb.append("  ");
            }
            sb.append("__");
        }

        sb.append("\n");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j == 0) {
                    sb.append(" |");
                }
                sb.append(" ").append(worldGrid[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
