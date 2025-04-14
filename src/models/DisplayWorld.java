package models;

import java.util.HashMap;

public class DisplayWorld extends GameWorld{
    private char[][] displayGrid;
    private GameWorld sourceWorld;

    
    public DisplayWorld(GameWorld sourceWorld) {
        this.sourceWorld = sourceWorld;
        displayGrid = new char[sourceWorld.getHeight()][sourceWorld.getWidth()];
        for (int i = 0; i < sourceWorld.getHeight(); i++) {
            for (int j = 0; j < sourceWorld.getWidth(); j++) {
                displayGrid[i][j] = ' '; // Initially everything is hidden
            }
        }
    }

    public void revealArea(GamePosition pos) {
        revealSurrounding(pos);
        revealTile(pos);
    }

    protected void revealSurrounding(GamePosition pos) {
    HashMap<String, Character> surrounding = sourceWorld.scanSurrounding(pos);

        for (Move.Direction direction : Move.Direction.values()) {
            Move move = new Move(direction);
            int newRow = pos.getRow() + move.getDY();
            int newCol = pos.getColumn() + move.getDX();

            if (sourceWorld.isDirectionOutBounds(pos, move)) {
                String key = direction.toString(); 
                char tile = surrounding.getOrDefault(key, ' ');

                if (tile != ' ') {
                    displayGrid[newRow][newCol] = tile;
                }
            }
        }
    }

    protected void revealTile(GamePosition pos) {
        if (pos.isInBounds(getWidth(), getHeight())) {
            displayGrid[pos.getRow()][pos.getColumn()] = sourceWorld.getTile(pos);
        }
    }

    public String toString(Player player, GameWorld sourceWorld) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sourceWorld.getWidth(); i++) {
            if (i == 0) {
                sb.append("  ");
            }
            sb.append("___");
        }

        sb.append("\n");

        for (int i = 0; i < sourceWorld.getHeight(); i++) {
            for (int j = 0; j < sourceWorld.getWidth(); j++) {
                if (j == 0) {
                    sb.append(" |");
                }
                if (i == player.getPosY() && j == player.getPosX()) {
                    sb.append(player.getPlayerChar()).append(displayGrid[i][j]).append(" ");
                } else {
                    sb.append(" ").append(displayGrid[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
