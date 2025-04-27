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
        player.setPosition(new GamePosition(0, 0), world);

        DisplayWorld displayWorld = new DisplayWorld(world);

        // Main game loop
        while (isRunning) {
            Game.updateView(world, displayWorld, player, true);

            Tile event = Game.getEvent(world, player);
            world.setEmpty(player.getPosition());
            String message = Game.getEventAction(event, player);

            Game.updateView(world, displayWorld, player, true);

            if (message != "No Action was Taken") {
                System.out.println(message);
            }

            switch(Game.getAction()) {
                case "Quit":
                    System.out.println("THERE IS NO SAVE SYSTEM IMPLEMENTED YET!\n" +
                                        "IF YOU QUIT NOW, YOU WILL LOSE YOUR PROGRESS!");
                    System.out.println("Are you sure you want to quit? (Y/N)");
                    String quit = scanner.next();
                    if (quit.equalsIgnoreCase("Y")) {
                        isRunning = false;
                    } else {
                        System.out.println("Continuing game...");
                    }
                    break;
                case "Inventory":
                    Game.clearScreen();
                    System.out.println(player.getInventory().invToString());
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
