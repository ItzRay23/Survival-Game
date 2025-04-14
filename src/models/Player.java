package models;

public class Player extends PlayerStats{

    private char playerChar;

    public PlayerStats stats = new PlayerStats();

    public Player(char playerChar) {
        super();
        this.playerChar = playerChar;
    }

    public Player(char playerChar, int health, int thirst, int stamina, int hunger) {
        super(health, thirst, stamina, hunger);
        this.playerChar = playerChar;
    }

    public char getPlayerChar() {
        return playerChar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player Character: ").append(playerChar).append("\n");

        sb.append("HP   : ");
        for (int i = 0; i < getHealth(); i++) {
            if (i % 5 == 0) {
                sb.append("#");
            }
        }
        sb.append(" | ").append(getHealth()).append("\n");

        sb.append("HUNGR: ");
        for (int i = 0; i < getHunger(); i++) {
            if (i % 5 == 0) {
                sb.append("#");
            }
        }
        sb.append(" | ").append(getHunger()).append("\n");

        sb.append("THRST: ");
        for (int i = 0; i < getThirst(); i++) {
            if (i % 5 == 0) {
                sb.append("#");
            }
        }
        sb.append(" | ").append(getThirst()).append("\n");

        sb.append("ENRGY: ");
        for (int i = 0; i < getEnergy(); i++) {
            if (i % 5 == 0) {
                sb.append("#");
            }
        }
        sb.append(" | ").append(getEnergy()).append("\n");

        return sb.toString();
    }

    public GamePosition getPosition() {
        return new GamePosition(stats.getPosX(), stats.getPosY());
    }
}