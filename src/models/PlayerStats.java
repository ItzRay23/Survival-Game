package models;

import interfaces.IPlayer;

public class PlayerStats implements IPlayer<Integer> {
    private int health;
    private int thirst;
    private int stamina;
    private int hunger;
    public int maxHealth = 100;
    public int maxThirst = 100;
    public int maxStamina = 100;
    public int maxHunger = 100;
    
    public PlayerStats() {
        health = 100;
        thirst = 100;
        stamina = 100;
        hunger = 100;
    }

    public PlayerStats(int health, int thirst, int stamina, int hunger) {
        this.health = health;
        this.thirst = thirst;
        this.stamina = stamina;
        this.hunger = hunger;
    }

    /**
     * Changes the health of the player based on the number passed in.
     * If the number is negative, it will decrease the health. If the number is positive, it will increase the health.
     * @param num The change in health.
     */
    @Override
    public void changeHealth(int num) {
        health += num;
    }

    /**
     * Changes the thirst of the player based on the number passed in.
     * If the number is negative, it will decrease the thirst. If the number is positive, it will increase the thirst.
     * @param num The change in thirst.
     */
    @Override
    public void changeThirst(int num) {
        thirst += num;
    }

    /**
     * Changes the stamina of the player based on the number passed in.
     * If the number is negative, it will decrease the stamina. If the number is positive, it will increase the stamina.
     * @param num The change in stamina.
     */
    @Override
    public void changeStamina(int num) {
        stamina += num;
    }

    /**
     * Changes the hunger of the player based on the number passed in.
     * If the number is negative, it will decrease the hunger. If the number is positive, it will increase the hunger.
     * @param num The change in hunger.
     */
    @Override
    public void changeHunger(int num) {
        hunger += num;
    }

    @Override
    public Integer getHealth() {
        return health;
    }

    @Override
    public Integer getThirst() {
        return thirst;
    }

    @Override
    public Integer getEnergy() {
        return stamina;
    }

    @Override
    public Integer getHunger() {
        return hunger;
    }
}