package models;

public class PlayerStats{
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
    public void changeHealth(int num) {
        health += num;
    }

    /**
     * Changes the thirst of the player based on the number passed in.
     * If the number is negative, it will decrease the thirst. If the number is positive, it will increase the thirst.
     * @param num The change in thirst.
     */
    public void changeThirst(int num) {
        thirst += num;
    }

    /**
     * Changes the stamina of the player based on the number passed in.
     * If the number is negative, it will decrease the stamina. If the number is positive, it will increase the stamina.
     * @param num The change in stamina.
     */
    public void changeStamina(int num) {
        stamina += num;
    }

    /**
     * Changes the hunger of the player based on the number passed in.
     * If the number is negative, it will decrease the hunger. If the number is positive, it will increase the hunger.
     * @param num The change in hunger.
     */
    public void changeHunger(int num) {
        hunger += num;
    }

    /**
     * Gets the current health of the player.
     * @return Current health of the player.
     */
    public Integer getHealth() {
        return health;
    }

    /**
     * Gets the current thirst of the player.
     * @return Current thirst of the player.
     */
    public Integer getThirst() {
        return thirst;
    }

    /**
     * Gets the current stamina of the player.
     * @return Current stamina of the player.
     */
    public Integer getEnergy() {
        return stamina;
    }

    /**
     * Gets the current hunger of the player.
     * @return Current hunger of the player.
     */
    public Integer getHunger() {
        return hunger;
    }
}