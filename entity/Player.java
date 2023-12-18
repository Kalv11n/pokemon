package entity;

public class Player {
    public static int playerId;
    private int id;
    private Monster[] playerMonsters;

    public Player() {
        this.playerMonsters = new Monster[3];
        playerId++;
        this.id = playerId;
    }

    public int getId() {
        return this.id;
    }

    public void addMonster(Monster newMonster) {
        for (int i = 0; i < this.playerMonsters.length; i++) {
            if (this.playerMonsters[i] == null) {
                this.playerMonsters[i] = newMonster;
                return;
            }
        }
    }

    public Monster[] getPlayerMonsters(){
        return this.playerMonsters;
    }

}