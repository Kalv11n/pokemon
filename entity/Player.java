package entity;

public class Player {
    public static int playerId = 0;
    private int id;
    private Monster[] playerMonsters;
    private Monster inUseMonster;
    private boolean allowAttacking;

    public Player() {
        this.allowAttacking = true;
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

    public Monster getInUseMonster(){
        return this.inUseMonster;
    }

    public void setInUseMonster(Monster monster){
        this.inUseMonster = monster;
    }

    public boolean getAllowAttacking() {
        return this.allowAttacking;
    }

    public void setAllowAttacking(boolean allowing) {
        this.allowAttacking = allowing;
    }

    //---- Others
    public boolean allMonstersKO() {
        return (this.playerMonsters[0].getHp() == 0 
            && this.playerMonsters[1].getHp() == 0
            && this.playerMonsters[2].getHp() == 0
        );
    }
}