package reader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Scanner;

import entity.Monster;
import entity.types.TypeFire;
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
                throw new Exception("Unvalid configuration filename.");
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
                throw new Exception("Unvalid configuration filename.");
        }

        this.scanner.close();
    }

    private void readAttack() throws Exception {
        try {
            Monster monster = null;
            String line;
            boolean createMonster = false;

            while (this.scanner.hasNextLine()) {
                line = this.scanner.nextLine();

                switch (line) {
                    case "Monster":
                        if (createMonster) {
                            throw new Exception("Monster never ended");
                        }

                        createMonster = true;
                        TypeFire fire = new TypeFire();
                        monster = new Monster("Test", fire, 100, 100, 100);
                        
                        break;
                
                    case "EndMonster" :
                        if (!createMonster) {
                            throw new Exception("Monster never created");
                        }

                        createMonster = false;

                        break;

                    default:
                        if (monster == null) {
                            throw new Exception("Value not affected to a monster");
                        }

                        String[] args = this.parser.parse(line);
                        this.implement(monster, args);
                        break;
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    } 

    private void readMonster() {

    }

    private void readObject() {

    }

    private void readState() {

    }

    private void implement(Monster monster, String[] args) throws Exception {
        // Test if args[0] is an Monster attribute or a Type attribute
        if (!hasAttribute(monster, args[0])) {
            switch (args[0]) {
                default:
                    throw new Exception("Error configuration : no attribute '" + args[0] + "' in Type::class");
            }
        }

        switch (args[0]) {
            case "Name":
                monster.setName(args[1]);
                break;

            case "Type":
                // Select the type
                switch (args[1]) {
                    case "Electric":
                        break;

                    case "Water":
                        break;
                    
                    case "Fire":
                        break;
                    
                    case "Earth":
                        break;
                    
                    case "Nature":
                        break;
                    
                    default:
                        throw new Exception("Undefined type '" + args[1] + "'");
                }
                break;
            
            case "HP":
                monster.setHp(args[1]);
                break;
            
            case "Speed":
                monster.setSpeed(args[1]);
                break;
            
            case "Attack":
                monster.setAttack(args[1]);
                break;
            
            case "Defense":
                monster.setDefense(args[1]);
                break;
            
            default:
                throw new Exception("Error configuration : no attribute '" + args[0] + "' in Monster::class");
        }
    }

    public static boolean hasAttribute(Object obj, String attributeName) {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().equals(attributeName)) {
                return true;
            }
        }

        return false;
    }
}
