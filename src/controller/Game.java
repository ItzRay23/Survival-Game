package controller;

import models.*;

public class Game {

    private static void updateView(DisplayWorld world, Player player) {
        System.out.println(player.toString());
        System.out.println(world.toString());
    }

    public static void main(String[] args) {
        GameWorld world = new GameWorld(10, 10);
        world.generateWorld(world.getHeight(), world.getWidth());

        Player player = new Player('P', 100, 100, 100, 100);

        DisplayWorld displayWorld = new DisplayWorld(world); // pass world into display

        updateView(displayWorld, player);

        GamePosition pos = new GamePosition(0, 0);
        System.out.println("Scan of surrounding tiles at " + pos.toString());
        displayWorld.revealSurrounding(pos);
        updateView(displayWorld, player);

        pos = new GamePosition(1, 0);
    }
}