package models;

import java.util.Random;

public class Tile {

    public enum TileType {
        EMPTY, EVENT, ENCOUNTER, RESOURCE, EXIT
    }

    private static TileType type;
    private static String description;

    public Tile(char tile) {
        type = setType(tile);
        description = setDescription(getType());
    }

    private TileType setType(char tile) {
        switch (tile) {
            case '*':
                return TileType.EMPTY;
            case '~':
                return TileType.RESOURCE;
            case '?':
                return TileType.EVENT;
            case '!':
                return TileType.ENCOUNTER;
            case 'X':
                return TileType.EXIT;
            default:
                return null;
        }
    }

    private String setDescription(TileType type) {
        String description = "None";
        Random rand;
        switch (type) {
            case RESOURCE:
                rand = new Random();
                int resourceType = rand.nextInt(3);
                switch (resourceType) {
                    case 0:
                        description = "You found a tree! Would you like to gather wood?";
                        break;
                    case 1:
                        description = "You found a bush! Would you like to gather berries?";
                        break;
                    case 2:
                        description = "You found a rock! Would you like to gather stone?";
                        break;
                }
                break;
            case EVENT:
                rand = new Random();
                int eventType = rand.nextInt(3);
                switch (eventType) {
                    case 0:
                        description = "You found a mysterious cave! It might be dangerous.";
                        break;
                    case 1:
                        description = "You found a strange artifact! It might be valuable.";
                        break;
                    case 2:
                        description = "You found a hidden path! It might lead to something interesting.";
                        break;
                }
                break;
            case ENCOUNTER:
                rand = new Random();
                int encounterType = rand.nextInt(3);
                switch (encounterType) {
                    case 0:
                        description = "You encountered a wild animal! Be careful.";
                        break;
                    case 1:
                        description = "You encountered a hostile survivor! Prepare for a fight.";
                        break;
                    case 2:
                        description = "You encountered a friendly survivor! They might help you.";
                        break;
                }
                break;
            case EXIT:
                description = "You found an exit! You can leave this area.";
                break;
            default:
                description = "This area is empty. Nothing to see here.";
                break;
        }
        return description;
    }

    public TileType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
