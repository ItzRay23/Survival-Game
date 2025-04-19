package controller;

import java.util.Scanner;

import models.*;
import java.util.HashSet;
import java.util.Set;

public class Game {

    private static Scanner scanner = new Scanner(System.in);
    /**
     * Updates the screen for any changes to the game.
     * THis includes the player's stats and their position in the world.
     *
     * @param sourceWorld The world that was generated.
     * @param world       The world that is displayed to the player.
     * @param player      The player that is playing the game.
     */
    public static void updateView(GameWorld sourceWorld, DisplayWorld world, Player player) {
        System.out.println(player.toString());
        System.out.println(world.toString(player, sourceWorld));
    }

    private static final Set<String> VALID_DIRECTIONS = new HashSet<>();

    static {
        for (Move.Direction dir : Move.Direction.values()) {
            VALID_DIRECTIONS.add(dir.toString().toUpperCase());
        }
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
     * 
     * @param sourceWorld
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
        System.out.println("Where do you want to move? Please enter a valid direction.");
        System.out.println("Valid directions are: N, S, E, W, NE, NW, SE, SW");
        String input = scanner.next().toUpperCase();
        if (validateDirectionInput(input, world, player)) {
            Move direction = new Move(Move.Direction.valueOf(input));
            return new GamePosition(player.getPosY() + direction.getDY(), player.getPosX() + direction.getDX());
        } else {
            System.out.println("Invalid direction. Please try again.");
            return getMovement(world, player);
        }
    }
}