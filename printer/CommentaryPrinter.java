package printer;

import java.util.List;

import entity.Attack;
import entity.Monster;
import entity.Player;
import entity.state.FloodedGroundState;

public class CommentaryPrinter {
    public static void printLaunch() {
        System.out.println(ColorPrinter.yellow(AsciiartPrinter.draw()));
    }

    public static void printCurrentPlayer(int idPlayer) {
        System.out.println(ColorPrinter.red("======== JOUEUR " + idPlayer + " ========") + "\n");
    }

    public static void printCurrentMonster(Player player) {
        System.out.println("Monstre actuel : " + player.getInUseMonster().toString() + "\n");  
    }

    public static void printStarting() {
        System.out.println(ColorPrinter.red("======== LE JEU VA COMMENCER ========") + "\n");
    }

    public static void printPlayerAdvantage(Player player) {
        System.out.println(ColorPrinter.red("======== JOUEUR " + player.getId() + " PREND L'AVANTAGE ========"));
    }

    public static void printDeck(Player player) {
        System.out.println(ColorPrinter.green("Ton deck : ") + "\n");

        Monster[] deckMonsters = player.getPlayerMonsters();
        for(int i=0; i < deckMonsters.length; i++){
            System.out.println(ColorPrinter.cyan("-> " + deckMonsters[i].toStringWithAttacks()));
        }

        System.out.print("\n" + ColorPrinter.orange("Objets : ["));

        Object[] deckObjects = player.getPlayerObjects();
        for(int i=0; i < deckObjects.length - 1; i++){
            System.out.print(ColorPrinter.orange(deckObjects[i] + ", "));
        }

        System.out.print(ColorPrinter.orange(deckObjects[deckObjects.length - 1] + ""));

        System.out.print(ColorPrinter.orange("]") + "\n");
    }

    public static void printCantUse(Attack attack) {        
        System.out.println(ColorPrinter.yellow(attack.getName()) + " ne peux plus être utilisé !");
    }

    // Lists
    public static void printMonsters() {        
        for(int i=1; i <= Monster.findAll().size(); i++){
            System.out.println("[" + ColorPrinter.red(Integer.toString(i)) + "]\t" + Monster.find((i - 1)));
        }

        System.out.println();
    }

    public static void printAttacks(Monster monster) {
        List<Attack> attacks = Attack.findByType(monster.getType());
        
        for(int j=0; j < attacks.size(); j++){
            System.out.println("[" + ColorPrinter.red(Integer.toString(j + 1)) + "] " + ColorPrinter.yellow(attacks.get(j).toString()));
        }

        System.out.println();
    }

    public static void printObjects() {
        for(int i = 0; i < entity.objects.Object.objects.size(); i++){
            System.out.println("[" + ColorPrinter.red((i + 1) + "") + "] "  + entity.objects.Object.find((i)));
        }

        System.out.println();
    }

    public static void printMonsterAttacks(Monster monster) {
        System.out.println();

        int i = 0;

        for (; i<4; i++) {
            System.out.println("[" + ColorPrinter.red((i + 1) + "") + "] " + ColorPrinter.cyan(monster.getAttacks()[i].toString()));
        }

        System.out.println();
    }

    public static void printPlayerMonsters(Player player) {
        System.out.println();

        int i = 0;

        for(; i < player.getPlayerMonsters().length; i++){
            System.out.println("[" + ColorPrinter.red((i + 1) + "") + "] " + ColorPrinter.cyan(player.getPlayerMonsters()[i].toString()));
        }

        System.out.println();
    }

    public static void printPlayerObjects(Player player) {
        System.out.println();

        int i = 0;

        for(; i < player.getPlayerObjects().length; i++){
            System.out.println("[" + ColorPrinter.red((i + 1) + "") + "] " + ColorPrinter.orange(player.getPlayerObjects()[i].toString()));
        }

        System.out.println();
    }

    public static void printWinner(Player player) {
        System.out.println(ColorPrinter.green("Joueur " + player.getId() + " a gagné !!!"));
    }

    // Select Infos
    public static void printSelectMonster() {
        System.out.println(ColorPrinter.purple("Choisissez 3 Pokemons") + "\n");
    }

    public static void printSelectObject() {
        System.out.println(ColorPrinter.purple("Choisissez 5 objets") + "\n");
    }

    // Select Requests
    public static void printSelectMonsterRequest() {
        System.out.print(ColorPrinter.green("Veuillez saisir un id de Pokemon : "));
    }

    public static void printSelectAttackRequest() {
        System.out.print(ColorPrinter.green("Veuillez saisir un id d'attaque : "));
    }

    public static void printSelectObjectRequest() {
        System.out.print(ColorPrinter.green("Veuillez saisir un id d'objet : "));
    }

    public static void printSelectResponse(String choice) {
        System.out.println(ColorPrinter.purple("-> " + choice) + "\n");
    }

    // Ground Flooded info
    public static void printGroundInfo() {
        if (FloodedGroundState.flooded) {
            System.out.println(ColorPrinter.purple("Le terrain est innondé !") + "\n");
        } else {
            System.out.println(ColorPrinter.purple("Le terrain est neutre.") + "\n");
        }
    }

    public static void printGroundNotFlooded() {
        System.out.println(ColorPrinter.purple("Le terrain n'est plus innondé."));
    }

    // Attacks infos
    public static void printIsEfficient() {
        System.out.println(ColorPrinter.purple("C'est très efficace !"));
    } 

    public static void printIsNotEfficient() {
        System.out.println(ColorPrinter.purple("Ce n'est pas très efficace !"));
    } 

    public static void printAttack(Monster currentMonster, Monster ennemyMonster) {
        System.out.println("\n" + ColorPrinter.blue(currentMonster.getName()) + " attaque " + ColorPrinter.blue(ennemyMonster.getName()) + " avec " 
        + ColorPrinter.yellow(currentMonster.getInUseAttack().getName()) + " !");
    }

    public static void printRemainHp(Monster monster) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " (PV restants : " + ColorPrinter.green(monster.getHp() + "") + ")");
    }

    public static void printKoChange(Player player) {
        System.out.print(ColorPrinter.red(player.getInUseMonster().getName()) + " est KO, vous devez changer de monstre.\n");
    }

    public static void printKo(Monster monster) {
        System.out.println(ColorPrinter.red(monster.getName() + " est KO !"));
    }

    public static void printKoAll(Player player) {
        System.out.println(ColorPrinter.red("Joueur " + player.getId() + ", tous les monstres sont KO.") + "\n");
    }

    public static void printDamage(Monster monster) {
        System.out.println("Dégats infligés : " + ColorPrinter.red(monster.getDamageReceived() + ""));
    }

    public static void printFailed(Monster monster) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " rate son attaque !");
    }

    public static void printSlide(Monster monster, int damage) {
        System.out.println(monster.getName() + " a glissé ! (Dégats subis : " + ColorPrinter.red(damage + "") + ")");
    }

    // Menu
    public static void printUseObject() {
        System.out.print(ColorPrinter.green("UTILISER UN OBJET") + " : [" + ColorPrinter.red("O") + "] : Oui | [" + ColorPrinter.red("N") + "] : Non ");
    }

    public static void printUseObjectResponse(Player player, entity.objects.Object object) {
        System.out.println("\n" + ColorPrinter.purple("Joueur " + player.getId() + " utilise l'objet : " + object.getName()));
    }

    public static void printChangeMonster() {
        System.out.print(ColorPrinter.green("CHANGER DE MONSTRE") + " : [" + ColorPrinter.red("O") + "] : Oui | [" + ColorPrinter.red("N") + "] : Non ");
    }

    public static void printChangeMonsterResponse(Monster monster) {
        System.out.println(ColorPrinter.green("\n" + monster.getName() + " ! A toi de jouer !"));
    }

    // States
    public static void printNotParalyze(Monster monster) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " n'est plus paralysé !");
    }

    public static void printParalyze(Monster monster) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " est paralysé !");
    }

    public static void printNotPoison(Monster monster) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " n'est plus empoisonné !");
    }

    public static void printPoison(Monster monster, int damage) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " est empoisonné ! (Dégats subis : " + ColorPrinter.red(damage + "") + ")");
    }

    public static void printNotHide(Monster monster) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " n'est plus caché !");
    }

    public static void printHide(Monster monster) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " est caché !");
    }

    public static void printNotBurn(Monster monster) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " n'est plus brulé !");
    }

    public static void printBurn(Monster monster, int damage) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " est brulé ! (Dégats subis : " + ColorPrinter.red(damage + "") + ")");
    }

    public static void printHeal(Monster monster, int hp) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " a récupéré " +  ColorPrinter.green(hp - monster.getHp() + "") + " HP ! ");
    }

    // Capacity
    public static void printBurnCapacity(Monster currentMonster, Monster ennemyMonster) {
        System.out.println(ColorPrinter.blue(currentMonster.getName()) + " a brulé " + ColorPrinter.blue(ennemyMonster.getName()) + " !");
    }

    public static void printFloodCapacity(Monster monster) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " a innondé le terrain !");
    }

    public static void printParalyzeCapacity(Monster currentMonster, Monster ennemyMonster) {
        System.out.println(ColorPrinter.blue(currentMonster.getName()) + " a paralysé " + ColorPrinter.blue(ennemyMonster.getName()) + " !");
    }

    public static void printPoisonCapacity(Monster currentMonster, Monster ennemyMonster) {
        System.out.println(ColorPrinter.blue(currentMonster.getName()) + " a empoisonné " + ColorPrinter.blue(ennemyMonster.getName()) + " !");
    }

    public static void printHideCapacity(Monster monster) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " se cache !");
    }

    public static void printAlterateCapacity(Monster monster) {
        System.out.println(ColorPrinter.blue(monster.getName()) + " n'est plus altéré !");
    }
}
