package models;

public class Item {
    private String name;
    private String description;
    private int value;
    private int quantity;

    public Item(String name, String description, int value) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.quantity = 1;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public int getQuantity() {
        return quantity;
    }

    public int addQuantity(int amount) {
        quantity += amount;
        return quantity;
    }

    public int removeQuantity(int amount) {
        if (quantity - amount < 0) {
            return 0;
        }
        quantity -= amount;
        return quantity;
    }
}
