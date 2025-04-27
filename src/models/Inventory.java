package models;

import java.util.HashMap;

public class Inventory {
    private static HashMap<String, Item> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        if (inventory.containsKey(item.getName())) {
            inventory.get(item.getName()).addQuantity(quantity);
        } else {
            inventory.put(item.getName(), item);
        }
    }

    public static void removeItem(Item item, int quanity)  {
        if (inventory.containsKey(item.getName())) {
            int remainingQuantity = inventory.get(item.getName()).removeQuantity(quanity);
            if (remainingQuantity == 0) {
                inventory.remove(item.getName());
            }
        }
    }

    public static HashMap<String, Item> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("           INVENTORY \n|");
        for (int i = 0; i < 30; i++) {
            str.append("-");
        }
        str.append("\n");

        String title = "ITEM NAME";
        str.append("|").append(title);
        for (int i = title.length(); i < 25; i++) {
            str.append(" ");
        }

        str.append("| QTY\n").append("|");

        for (int i = 0; i < 30; i++) {
            str.append("-");
        }

        if (inventory.isEmpty()) {
            str.append("\n| No items in inventory\n");
            return str.toString();
        }

        int index = 1;
        for (String key : inventory.keySet()) {
            Item item = inventory.get(key);
            str.append("\n").append("| ").append(index + ". ").append(item.getName());
            for (int i = item.getName().length(); i < 21; i++) {
                str.append(" ");
            }
            str.append("| ").append(item.getQuantity());
            index++;
        }
        
        return str.toString();
    }
}
