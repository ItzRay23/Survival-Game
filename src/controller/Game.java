package controller;

import models.*;

public class Game {

    private static void updateView(GameWorld world, Player player) {
        System.out.println(player.toString());
        System.out.print(world.toString());
    }
    
    public static void main(String[] args) {
        GameWorld world = new GameWorld();
        world.generateWorld(world.getWidth(), world.getHeight());
        Player player = new Player('P', 100, 100, 100, 100);
    }
}
