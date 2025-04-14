package models;


/**
 * Interface for the GameWorld class. This interface defines the world the player will be able to explore using any method(s) such as movePlayer. The world will be generated randomly and the player will be able to move around in it. The game will end when the player wins the game or when the player dies. The game can be reset at any time using the resetGame method.
 */
public interface IGameWorld {
    public void generateWorld(int width, int height);
    // public void checkGameOver();
    // public void resetGame();
    // public PlayerPosition movePlayer(Move direction);
}