package controller;

import java.util.Scanner;

import models.*;
import java.util.HashSet;
import java.util.Set;

public class Game {

    private static void updateView(GameWorld sourceWorld, DisplayWorld world, Player player) {
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

    private static void printGeneratedWorld(GameWorld sourceWorld) {
        System.out.println("Generated World: \n" + sourceWorld.toString());
    }

    private static GamePosition getMovement(GameWorld world, Player player) {
        Scanner scanner = new Scanner(System.in);
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

    private static boolean isPlaying(GameWorld world) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to continue playing? (yes/no)");
        String input = scanner.next().toLowerCase();
        while (input.equals("yes") || input.equals("no")) {
            if (input.equals("yes")) {
                scanner.close();
                return true;
            } else if (input.equals("no")) {
                scanner.close();
                return false;
            }
        }
        Player player = new Player('P', 100, 100, 100, 100);
        if (player.getPosition() == null) {
            player.setPosition(new GamePosition(0, 0), world);  // Ensure starting position is initialized
        }
        scanner.close();
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game!");
        System.out.println("Please enter the height and width of the world (e.g., 10 10): ");
        int height = scanner.nextInt();
        int width = scanner.nextInt();

        GameWorld world = new GameWorld(height, width);
        world.generateWorld(world.getHeight(), world.getWidth());

        Player player = new Player('P', 100, 100, 100, 100);
        player.setPosition(new GamePosition(0, 0), world);  // starting position

        DisplayWorld displayWorld = new DisplayWorld(world);

        // Main game loop
        while (true) {
            displayWorld.revealArea(player.getPosition());  // Update surrounding tiles and the player's position
            updateView(world, displayWorld, player);
            
            // Get the player's movement and update position
            GamePosition newPosition = getMovement(world, player);
            System.out.println("New Position Offset: " + newPosition.toString());
            player.setPosition(newPosition, world);  // Update player's position
            GamePosition playerPosition = player.getPosition();
            System.out.println("Current Player Position: " + playerPosition.toString());

            // After updating the position, we also want to update the surrounding tiles again
            displayWorld.revealArea(player.getPosition());  // Refresh surrounding tiles based on new position
        }
    }
}