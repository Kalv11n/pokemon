package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import entity.Attack;
import entity.Monster;
import exception.ConfigurationErrorException;
import exception.InstanciatorErrorException;
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

    public void read(String filename) {
        try {
            switch (filename) {
                case "attack":
                    this.file = new File(DIR + "/attack.config");
                    break;

                case "monster":
                    this.file = new File(DIR + "/monster.config");
                    break;
                
                default:
                    throw new ConfigurationErrorException("Invalid configuration filename.");
            }

            this.scanner = new Scanner(this.file);
            
            switch (filename) {
                case "attack":
                    this.readAttack();
                    break;

                case "monster":
                    this.readMonster();
                    break;
                
                default:
                    throw new ConfigurationErrorException("Invalid configuration filename.");
            }

            this.scanner.close();
        } catch (InstanciatorErrorException e) {
            System.err.println(e.getMessage());
        } catch (ConfigurationErrorException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    private void readMonster() throws ConfigurationErrorException {
        Monster monster = null;
        String line;
        boolean implementingMonster = false;
        boolean errorInitializationMonster = false;

        while (this.scanner.hasNextLine()) {
            line = this.scanner.nextLine();

            if (line.equals("")) {
                continue;
            }

            switch (line) {
                case "Monster":                    
                    errorInitializationMonster = false;
                    implementingMonster = true;
                    monster = new Monster();
                    
                    break;
            
                case "EndMonster" :
                    if (errorInitializationMonster) {
                        errorInitializationMonster = false;
                    }

                    monster = null;
                    implementingMonster = false;

                    break;

                default:
                    if (errorInitializationMonster) {
                        continue;
                    }

                    if (!implementingMonster) {
                        throw new ConfigurationErrorException("Monster after '" + Monster.findLast().getName() + "' not created");
                    }

                    String[] args = this.parser.parse(line);

                    MonsterInstanciator monsterInstanciator = new MonsterInstanciator();

                    try {
                        monsterInstanciator.implement(monster, args);
                    } catch(InstanciatorErrorException e) {
                        Monster.removeLast();
                        errorInitializationMonster = true;
                    }

                    break;
            }
        }    
    } 

    private void readAttack() throws ConfigurationErrorException, InstanciatorErrorException {
        Attack attack = null;
        String line;
        boolean implementingAttack = false;
        boolean errorInitializationAttack = false;

        while (this.scanner.hasNextLine()) {
            line = this.scanner.nextLine();

            if (line.equals("")) {
                continue;
            }

            switch (line) {
                case "Attack":
                    implementingAttack = true;
                    attack = new Attack();
                    
                    break;
            
                case "EndAttack" :
                    if (errorInitializationAttack) {
                        errorInitializationAttack = false;
                    }

                    attack = null;
                    implementingAttack = false;

                    break;

                default:
                    if (errorInitializationAttack) {
                        continue;
                    }

                    if (!implementingAttack) {
                        throw new ConfigurationErrorException("Attack after '" + Attack.findLast().getName() + "' not created");
                    }

                    String[] args = this.parser.parse(line);
                    
                    AttackInstanciator attackInstanciator = new AttackInstanciator();

                    try {
                        attackInstanciator.implement(attack, args);
                    } catch(InstanciatorErrorException e) {
                        Attack.removeLast();
                        errorInitializationAttack = true;
                    }

                    break;
            }
        }
    }
}
