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

    public void addMonster(Monster newMonster) {
        for (int i = 0; i < this.playerMonsters.length; i++) {
            if (this.playerMonsters[i] == null) {
                this.playerMonsters[i] = newMonster;
                System.out.println("Monstre ajouté");
                return;
            }
        }
        System.out.println("La liste est pleine");
    }

}