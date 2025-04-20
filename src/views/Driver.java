package views;

import java.util.Scanner;

import models.*;
import controller.*;

public class Driver {
    public static void main(String[] args) {
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
        while (true) {
            Game.clearScreen();
            displayWorld.revealArea(player.getPosition());  // Update surrounding tiles and the player's position
            Game.updateView(world, displayWorld, player);
            Game.getEvent(world, player);
            
            // Get the player's movement and update position
            GamePosition newPosition = Game.getMovement(world, player);
            player.setPosition(newPosition, world);  // Update player's position
        }
    }
}
