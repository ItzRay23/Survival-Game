package interfaces;

import models.GamePosition;

/**
 * Interface for the GameWorld class. This interface defines the world the player will be able to explore.
 */
public interface IGameWorld {
    
    public void tileRandomizer(GamePosition pos);
    
    default void generateWorld(int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tileRandomizer(new GamePosition(i, j));
            }
        }
    }
}