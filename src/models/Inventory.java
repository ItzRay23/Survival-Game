package models;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, Item> inventory;

    public Inventory() {
        this.inventory = new HashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Inventory \n");
        for (int i = 0; i < 30; i++) {
            str.append("-");
        }
        str.append("\n");

        for (String key : inventory.keySet()) {
            Item item = inventory.get(key);
            str.append("| ").append(item.getName());
        }
        
        return str.toString();
    }
}
