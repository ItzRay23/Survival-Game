package controller;

import java.util.Scanner;

import models.*;

public class Game {

    private static void updateView(GameWorld sourceWorld, DisplayWorld world, Player player) {
        System.out.println(player.toString());
        System.out.println(world.toString(player, sourceWorld));
    }

    private static boolean validateDirectionInput(String input) {
        for (Move.Direction dir : Move.Direction.values()) {
            if (input.equalsIgnoreCase(dir.toString())) {
                return true;
            }
        }
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

        DisplayWorld displayWorld = new DisplayWorld(world); // pass world into display

        updateView(world, displayWorld, player);

        GamePosition pos = new GamePosition(0, 0);
        System.out.println("Generated World: \n" + world.toString());
        System.out.println("Scan of surrounding tiles at " + pos.toString());
        displayWorld.revealArea(pos);
        updateView(world, displayWorld, player);

        pos = new GamePosition(1, 0);

        scanner.close();
    }
}