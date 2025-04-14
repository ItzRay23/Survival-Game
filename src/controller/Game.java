package controller;

import models.*;
import java.util.HashMap;

public class Game {

    private static void updateView(GameWorld world, Player player) {
        System.out.println(player.toString());
        System.out.println(world.toString());
    }

    
    public static void main(String[] args) {
        GameWorld world = new GameWorld(10, 10);
        world.generateWorld(world.getWidth(), world.getHeight());
        Player player = new Player('P', 100, 100, 100, 100);

        updateView(world, player);

        GamePosition pos = new GamePosition(5, 5);
        System.out.println("Scan of surrounding tiles at " + pos.toString());
        world.revealSurrounding(pos);

    }
}