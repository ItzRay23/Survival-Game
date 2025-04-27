package views;

import java.util.Scanner;

import models.*;
import controller.*;

public class Driver {
    public static void main(String[] args) {
        boolean isRunning = true;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game!");
        System.out.println("Please enter the height and width of the world (e.g., 10 10): ");
        int height = scanner.nextInt();
        int width = scanner.nextInt();

        GameWorld world = new GameWorld(height, width);
        world.generateWorld(world.getHeight(), world.getWidth());

        Player player = new Player('P');
        player.setPosition(new GamePosition(0, 0), world);  // starting position

        DisplayWorld displayWorld = new DisplayWorld(world);

        // Main game loop
        while (isRunning) {
            displayWorld.revealArea(player.getPosition());
            Game.updateView(world, displayWorld, player, true);

            Tile event = Game.getEvent(world, player);
            world.setEmpty(player.getPosition());
            String message = Game.getEventAction(event, player);

            Game.updateView(world, displayWorld, player, true);

            System.out.println(message);
            System.out.println("You are on a " + world.getTile(player.getPosition()) + " tile.");

            switch(Game.getAction()) {
                case "Quit":
                    isRunning = false;
                    break;
                case "Inventory":
                    System.out.println(player.getInventory().toString());
                    Game.getInvAction();
                    break;
                case "Move":
                    Game.movePlayer(world, player);
            }
        }
        scanner.close();
        System.out.println("Quitting game...");
    }
}
