package controller;

import java.util.Scanner;

import models.*;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Game {

    private static Scanner scanner = new Scanner(System.in);

    private static final Set<String> VALID_DIRECTIONS = new HashSet<>();

    static {
        for (Move.Direction dir : Move.Direction.values()) {
            VALID_DIRECTIONS.add(dir.toString().toUpperCase());
        }
    }

    /**
     * Updates the screen for any changes to the game.
     * This includes the player's stats and their position in the world.
     *
     * @param sourceWorld The world that was generated.
     * @param world       The world that is displayed to the player.
     * @param player      The player that is playing the game.
     */
    public static void updateView(GameWorld sourceWorld, DisplayWorld world, Player player, boolean toClear) {
        if (toClear) {
            clearScreen();
        }
        System.out.println(player.toString());
        System.out.println(world.toString(player, sourceWorld));
    }

    /**
     * Validates the player's input direction by checking if it matches a valid direction
     * and ensures the resulting move is within the bounds of the game world.
     *
     * @param input  The direction input provided by the player.
     * @param world  The game world to validate the move against.
     * @param player The player whose position is being validated.
     * @return true if the direction is valid and within bounds, false otherwise.
     */
    private static boolean validateDirectionInput(String input, GameWorld world, Player player) {
        String upperInput = input.toUpperCase();
        if (!VALID_DIRECTIONS.contains(upperInput)) {
            return false;
        }
        Move direction = new Move(Move.Direction.valueOf(upperInput));
        return world.isDirectionInBounds(player.getPosition(), direction);
    }

    /**
     * Converts the valid directions to a string representation for display.
     *
     * @param surrounding The surrounding tiles of the player.
     * @param str         The StringBuilder to append the string representation to.
     * @return The string representation of the valid directions.
     */
    private static String printDirections(HashMap<String, Character> surrounding, StringBuilder str) {
        ArrayList<String> surroundingDirections = new ArrayList<>();

        for (Move.Direction direction : Move.Direction.values()) {
            surroundingDirections.add(direction.toString().toUpperCase());
        }

        int index = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    str.append(" . ");
                    continue;
                } else if (surrounding.get(surroundingDirections.get(index)) != ' ') {
                    str.append(" ").append(surroundingDirections.get(index)).append(" ");
                } else {
                    str.append("   ");
                }
                index++;
            }
            str.append("\n");
        }
        str.append("\n");
        return str.toString();
    }

    private static String getValidDirections(GameWorld world, Player player) {
        HashMap<String, Character> surrounding = world.scanSurrounding(player.getPosition());
        StringBuilder validDirections = new StringBuilder("Valid directions are: \n");
        return printDirections(surrounding, validDirections);
    }


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    /**
     * Prints the generated world to the console. [This method is for debugging purposes only.]
     * @param sourceWorld The world that was generated.
     */
    public static void printGeneratedWorld(GameWorld sourceWorld) {
        System.out.println("Generated World: \n" + sourceWorld.toString());
    }

    /**
     * Prompts the player for a movement direction and returns the new position.
     * If the input is invalid, it will recursively ask for a valid input.
     *
     * @param world  The game world to validate the move against.
     * @param player The player whose position is being updated.
     * @return The new position of the player after the move.
     */
    public static GamePosition getMovement(GameWorld world, Player player) {
        System.out.println("Where do you want to move? Please enter a valid direction. Each letter (Key) is positioned to be a direction in the world: ");
        System.out.println(getValidDirections(world, player));
        String input = scanner.nextLine().toUpperCase();
        if (validateDirectionInput(input, world, player)) {
            Move direction = new Move(Move.Direction.valueOf(input));
            return new GamePosition(player.getPosY() + direction.getDY(), player.getPosX() + direction.getDX());
        } else {
            System.out.println("Invalid direction. Please try again.");
            return getMovement(world, player);
        }
    }

    public static void movePlayer(GameWorld world, Player player) {
        GamePosition newPosition = getMovement(world, player);
        player.setPosition(newPosition, world);
    }

    /**
     * Gets the event that occurs when the player moves to a new tile.
     * @param world  The world that was generated.
     * @param player The player to get the position from.
     */
    public static Tile getEvent(GameWorld world, Player player) {
        GamePosition pos = player.getPosition();
        Tile tile = new Tile(world.getTile(pos));
        System.out.println("You are on a(n) " + tile.getType() + " tile.");
        System.out.println(tile.getDescription() + "\n");
        return tile;
    }

    private static String printActions() {
        StringBuilder actions = new StringBuilder();
        actions.append("[1] Move").append(" [2] Inventory").append(" [3] Quit");

        return actions.toString();
    }

    public static String getAction() {
        System.out.println("What would you like to do? \n" + printActions());
        String action = scanner.nextLine();
        switch (action) {
            case "1":
                System.out.println("You have chosen to move.");
                return "Move";
            case "2":
                System.out.println("You open your inventory.");
                return "Inventory";
            case "3":
                return "Quit";
            default:
                System.out.println("Invalid action {" + action + "}. Please try again.");
                return getAction();
        }
    }

    public static void getInvAction() {
        //TODO: Implement inventory actions after events are implemented.
        System.out.println("You are looking at your inventory. \n");
        System.out.println("Press enter for now since this is not implemented yet.");
        scanner.nextLine();
    }

    public static void getEventAction(Tile event, Player player) {
        Tile.TileType type = event.getType();
        int eventType = event.getEvent();
        switch(type){
            case RESOURCE:
                switch (eventType) {
                    case 0:
                        System.out.println("[1] Yes \n[2] No");
                        if(scanner.nextLine() == "1") {
                            System.out.println("You got 1 wood!");
                            player.getInventory().addItem(new Item("Wood", "A piece of wood", 1), 1);
                        }
                        break;
                    case 1:
                        System.out.println("[1] Yes \n[2] No");
                        if(scanner.nextLine() == "1") {
                            System.out.println("You got 4 berries!");
                            player.getInventory().addItem(new Item("Berry", "A bunch of berries", 4), 4);
                        }
                        break;
                    case 2:
                        System.out.println("[1] Yes \n[2] No");
                        if(scanner.nextLine() == "1") {
                            System.out.println("You got 1 stone!");
                            player.getInventory().addItem(new Item("Stone", "A chunk of stone", 1), 1);
                        }
                        break;
                }
            break;
            case EVENT:
                switch(eventType) {
                    case 0:
                        System.out.println("You found a treasure chest! You got 10 gold!");
                        player.getInventory().addItem(new Item("Gold", "A piece of gold", 10), 10);
                        break;
                    case 1:
                        System.out.println("You found a health potion! You got 1 health potion!");
                        player.getInventory().addItem(new Item("Health Potion", "A potion that restores health", 1), 1);
                        break;
                    case 2:
                        System.out.println("You found a magic wand! You got a magic wand!");
                        player.getInventory().addItem(new Item("Magic Wand", "A wand that casts spells", 1), 1);
                        break;
                }
            break;
            case ENCOUNTER:
                switch (eventType) {
                    case 0:
                        System.out.println("You encountered a wild animal! You need to fight it!");
                        break;
                    case 1:
                        System.out.println("You encountered a bandit! You need to fight it!");
                        break;
                    case 2:
                        System.out.println("You encountered a monster! You need to fight it!");
                        break;
                }
            break;
            default:
                break;
        }
    }
}