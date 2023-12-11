package entity;

public class Player {
    private int playerId;
    private Monster[] monsters;

    public Player() {
        this.monsters = new Monster[3];
    }

    public void addMonster(Monster newMonster) {
        for (int i = 0; i < this.monsters.length; i++) {
            if (this.monsters[i] == null) {
                this.monsters[i] = newMonster;
                System.out.println("Monstre ajouté");
                return;
            }
        }
        System.out.println("La liste est pleine");
    }

}
