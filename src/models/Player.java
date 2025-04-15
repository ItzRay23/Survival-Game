package models;

public class Player extends PlayerStats{

    private char playerChar;
    private int posX;
    private int posY;

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

        sb.append("HEALTH: ");
        for (int i = 0; i < getHealth(); i++) {
            if (i % 5 == 0) {
                sb.append("#");
            }
        }
        sb.append(" | ").append(getHealth()).append("\n");

        sb.append("HUNGER: ");
        for (int i = 0; i < getHunger(); i++) {
            if (i % 5 == 0) {
                sb.append("#");
            }
        }
        sb.append(" | ").append(getHunger()).append("\n");

        sb.append("THIRST: ");
        for (int i = 0; i < getThirst(); i++) {
            if (i % 5 == 0) {
                sb.append("#");
            }
        }
        sb.append(" | ").append(getThirst()).append("\n");

        sb.append("ENERGY: ");
        for (int i = 0; i < getEnergy(); i++) {
            if (i % 5 == 0) {
                sb.append("#");
            }
        }
        sb.append(" | ").append(getEnergy()).append("\n");

        return sb.toString();
    }

    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public GamePosition getPosition() {
        return new GamePosition(getPosY(), getPosX());
    }

    public void setPosition(GamePosition pos, GameWorld world) {
        if (world.canMoveTo(pos)) {
            setPosX(pos.getColumn());
            setPosY(pos.getRow());
        }
    }
}