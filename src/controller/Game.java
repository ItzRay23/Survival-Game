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
        world.revealArea(player.getPosition());
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
                return "Inventory";
            case "3":
                return "Quit";
            default:
                System.out.println("Invalid action {" + action + "}. Please try again.");
                return getAction();
        }
    }

    public static void getInvAction() {
        boolean inputIsValid = false;
        ArrayList<Item> indexedItems = new ArrayList<>();
        for (String key : Inventory.getInventory().keySet()) {
            indexedItems.add(Inventory.getInventory().get(key));
        }
        System.out.println("\nSelect an item or type [0] to exit: ");
        String input = scanner.nextLine();

        while (input.equals("\n") || input.equals(" ")) {
            System.out.println("Invalid input. Please try again.");
            input = scanner.nextLine();
        }

        while (!inputIsValid) {
            try {
                int index = Integer.parseInt(input) - 1;
                while(index < 0 || index >= indexedItems.size()) {
                    System.out.println("Index Out of Bounds! Please try again.");
                    input = scanner.nextLine();
                    index = Integer.parseInt(input) - 1;
                }
                inputIsValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Not a number! Please try again.");
                input = scanner.nextLine();
            }
        }

        if (input.equals("0")) {
            return;
        } else if (indexedItems.isEmpty()) {
            System.out.println("You have no items in your inventory.");
        }
        else {
            Item selectedItem = indexedItems.get(Integer.parseInt(input) - 1);
            System.out.println("You have selected " + selectedItem.getName() + ". What would you like to do? \n" +
                    "[1] Use Item" + " [2] Drop Item" + " [3] Select Another Item");
            String action = scanner.nextLine();
            while (action.equals("\n") || Integer.parseInt(action) < 1 || Integer.parseInt(action) > 3) {
                System.out.println("Invalid input. Please try again.");
                action = scanner.nextLine();
            }
            switch (action) {
                case "1":
                    Inventory.removeItem(selectedItem, 1);
                    clearScreen();
                    System.out.println(Inventory.invToString());
                    System.out.println("You have used " + selectedItem.getName() + ".");
                    break;
                case "2":
                    Inventory.removeItem(selectedItem, 1);
                    clearScreen();
                    System.out.println(Inventory.invToString());
                    System.out.println("You have dropped " + selectedItem.getName() + ".");
                    break;
                case "3":
                    clearScreen();
                    System.out.println(Inventory.invToString());
                    break;
            }
        }
        getInvAction();
    }

    public static String getEventAction(Tile event, Player player) {
        Tile.TileType type = event.getType();
        int eventType = event.getEvent();
        String actionMessage = "No action was taken.";
        String input;
        switch(type) {
            case RESOURCE:
                switch (eventType) {
                    case 0:
                        System.out.println("[y] Yes \n[n] No");
                        input = scanner.nextLine();
                        while (!input.equals("y") && !input.equals("n")) {
                            System.out.println("Invalid input. Please try again.");
                            input = scanner.nextLine();
                        }
                        if (input.equals("y")) {
                            actionMessage = "You got 1 wood!";
                            player.getInventory().addItem(new Item("Wood", "A piece of wood", 1), 1);
                        }
                        break;
                    case 1:
                        System.out.println("[y] Yes \n[n] No");
                        input = scanner.nextLine();
                        while (!input.equals("y") && !input.equals("n")) {
                            System.out.println("Invalid input. Please try again.");
                            input = scanner.nextLine();
                        }
                        if (input.equals("y")) {
                            actionMessage = "You got 4 berries!";
                            player.getInventory().addItem(new Item("Berry", "A bunch of berries", 4), 4);
                        }
                        break;
                    case 2:
                        System.out.println("[y] Yes \n[n] No");
                        input = scanner.nextLine();
                        while (!input.equals("y") && !input.equals("n")) {
                            System.out.println("Invalid input. Please try again.");
                            input = scanner.nextLine();
                        }
                        if (input.equals("y")) {
                            actionMessage = "You got 1 stone!";
                            player.getInventory().addItem(new Item("Stone", "A chunk of stone", 1), 1);
                        }
                        break;
                }
            break;
            case EVENT:
                switch(eventType) {
                    case 0:
                        //TODO: Placeholder, implement later.
                        actionMessage = "You found a treasure chest! You got 10 gold!\n" +
                                "Press enter to continue...";
                        player.getInventory().addItem(new Item("Gold", "A piece of gold", 10), 10);
                        break;
                    case 1:
                        //TODO: Placeholder, implement later.
                        actionMessage = "You found a health potion! You got 1 health potion!\n" +
                                "Press enter to continue...";
                        player.getInventory().addItem(new Item("Health Potion", "A potion that restores health", 1), 1);
                        break;
                    case 2:
                        //TODO: Placeholder, implement later.
                        actionMessage = "You found a magic wand! You got a magic wand!\n" +
                                "Press enter to continue...";
                        player.getInventory().addItem(new Item("Magic Wand", "A wand that casts spells", 1), 1);
                        break;
                }
            scanner.nextLine();
            break;
            case ENCOUNTER:
                switch (eventType) {
                    case 0:
                        actionMessage = "You encountered a wild animal! You need to fight it!";
                        break;
                    case 1:
                        actionMessage = "You encountered a bandit! You need to fight it!";
                        break;
                    case 2:
                        actionMessage = "You encountered a monster! You need to fight it!";
                        break;
                }
            scanner.nextLine();
            break;
            default:
                break;
        }
        return actionMessage;
    }
}