package models;

import java.util.Random;

public class Tile {

    public enum TileType {
        EMPTY, EVENT, ENCOUNTER, RESOURCE, EXIT
    }

    private TileType type;
    private String description;

    public Tile(char tile) {
        this.type = setType(tile);
        this.description = setDescription(type);
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
        switch (type) {
            case RESOURCE:
                Random rand = new Random();
                int resourceType = rand.nextInt(3);
                switch (resourceType) {
                    case 0:
                        description = "You found a tree! Would you like to gather wood?";
                    case 1:
                        description = "You found a bush! Would you like to gather berries?";
                    case 2:
                        description = "You found a rock! Would you like to gather stone?";
                }
            case EVENT:
                rand = new Random();
                int eventType = rand.nextInt(3);
                switch (eventType) {
                    case 0:
                        description = "You found a mysterious cave! It might be dangerous.";
                    case 1:
                        description = "You found a strange artifact! It might be valuable.";
                    case 2:
                        description = "You found a hidden path! It might lead to something interesting.";
                }
            case ENCOUNTER:
                rand = new Random();
                int encounterType = rand.nextInt(3);
                switch (encounterType) {
                    case 0:
                        description = "You encountered a wild animal! Be careful.";
                    case 1:
                        description = "You encountered a hostile survivor! Prepare for a fight.";
                    case 2:
                        description = "You encountered a friendly survivor! They might help you.";
                }
            case EXIT:
                description = "You found an exit! You can leave this area.";
            default:
                description = "You are in an empty area. Nothing special here.";
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
