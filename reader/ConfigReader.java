package reader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import entity.Attack;
import entity.Monster;
import instanciator.AttackInstanciator;
import instanciator.MonsterInstanciator;
import parser.ConfigParser;

public class ConfigReader {
    public final String DIR = "./config";

    private ConfigParser parser;
    private File file;
    private Scanner scanner;

    public ConfigReader(ConfigParser parser) {
        this.parser = parser;
    }

    public void read(String filename) throws Exception {
        switch (filename) {
            case "attack":
                this.file = new File(DIR + "/attack.config");
                break;

            case "monster":
                this.file = new File(DIR + "/monster.config");
                break;
            
            case "object":
                this.file = new File(DIR + "/object.config");
                break;
            
            case "state":
                this.file = new File(DIR + "/state.config");
                break;
            
            default:
                throw new Exception("Invalid configuration filename.");
        }

        this.scanner = new Scanner(this.file);
        
        switch (filename) {
            case "attack":
                this.readAttack();
                break;

            case "monster":
                this.readMonster();
                break;
            
            case "object":
                this.readObject();
                break;
            
            case "state":
                this.readState();
                break;
            
            default:
                throw new Exception("Invalid configuration filename.");
        }

        this.scanner.close();
    }

    private void readMonster() throws Exception {
        try {
            Monster monster = null;
            String line;
            boolean createMonster = false;

            while (this.scanner.hasNextLine()) {
                line = this.scanner.nextLine();

                if (line.equals("")) {
                    continue;
                }

                switch (line) {
                    case "Monster":
                        if (createMonster) {
                            throw new Exception("Monster never ended");
                        }

                        createMonster = true;
                        monster = new Monster();
                        
                        break;
                
                    case "EndMonster" :
                        if (!createMonster) {
                            throw new Exception("Monster never created");
                        }

                        createMonster = false;
                        System.out.println(monster.toString() + "\n");

                        break;

                    default:
                        if (monster == null) {
                            throw new Exception("Value not affected to a monster");
                        }

                        String[] args = this.parser.parse(line);

                        MonsterInstanciator monsterInstanciator = new MonsterInstanciator();
                        monsterInstanciator.implement(monster, args);

                        break;
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    } 

    private void readAttack() throws Exception {
        try {
            Attack attack = null;
            String line;
            boolean createAttack = false;

            while (this.scanner.hasNextLine()) {
                line = this.scanner.nextLine();

                if (line.equals("")) {
                    continue;
                }

                switch (line) {
                    case "Attack":
                        if (createAttack) {
                            throw new Exception("Attack never ended");
                        }

                        createAttack = true;
                        attack = new Attack();
                        
                        break;
                
                    case "EndAttack" :
                        if (!createAttack) {
                            throw new Exception("Attack never created");
                        }

                        createAttack = false;
                        System.out.println(attack.toString() + "\n");

                        break;

                    default:
                        if (attack == null) {
                            throw new Exception("Value not affected to an attack");
                        }

                        String[] args = this.parser.parse(line);
                        
                        AttackInstanciator attackInstanciator = new AttackInstanciator();
                        attackInstanciator.implement(attack, args);

                        break;
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject() {

    }

    private void readState() {

    }
}
