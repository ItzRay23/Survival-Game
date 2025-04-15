package models;

public interface IPlayer<T> {
    public T getHealth();
    public T getThirst();
    public T getEnergy();
    public T getHunger();
    public void changeHealth(int num);
    public void changeThirst(int num);
    public void changeStamina(int num);
    public void changeHunger(int num);
}
