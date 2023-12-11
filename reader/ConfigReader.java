package reader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.Scanner;

import entity.Monster;
import entity.types.TypeEarth;
import entity.types.TypeElectric;
import entity.types.TypeFire;
import entity.types.TypeInsect;
import entity.types.TypeNature;
import entity.types.TypePlant;
import entity.types.TypeWater;
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
                        TypeFire fire = new TypeFire();
                        monster = new Monster("Test", fire, 100, 100, 100);
                        
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
                        this.implement(monster, args);
                        break;
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    } 

    private void readAttack() {

    }

    private void readObject() {

    }

    private void readState() {

    }

    private int implement(Monster monster, String[] args) throws Exception {
        // Test if args[0] is an Monster attribute or a Type attribute
        if (!hasAttribute(monster, args[0])) {
            switch (args[0]) {
                case "Paralysis": // TypeElectric
                    if (!(monster.getType() instanceof TypeElectric)) {
                        throw new Exception("Error configuration : Undefined '" + args[0] + "' attribute for TypeElectric::class");
                    }

                    TypeElectric elec = (TypeElectric) monster.getType();
                    elec.setParalysis(Float.parseFloat(args[1]));

                    break;
                
                case "Hide": // TypeEarth
                    if (!(monster.getType() instanceof TypeEarth)) {
                        throw new Exception("Error configuration : Undefined '" + args[0] + "' attribute for TypeEarth::class");
                    }

                    TypeEarth earth = (TypeEarth) monster.getType();
                    earth.setHide(Float.parseFloat(args[1]));

                    break;

                case "Burn": // TypeFire
                    if (!(monster.getType() instanceof TypeFire)) {
                        throw new Exception("Error configuration : Undefined '" + args[0] + "' attribute for TypeFire::class");
                    }

                    TypeFire fire = (TypeFire) monster.getType();
                    fire.setBurn(Float.parseFloat(args[1]));

                    break;
                
                case "Poison": // TypeInsect
                    if (!(monster.getType() instanceof TypeInsect)) {
                        throw new Exception("Error configuration : Undefined '" + args[0] + "' attribute for TypeInsect::class");
                    }

                    TypeInsect insect = (TypeInsect) monster.getType();
                    insect.setPoison(Float.parseFloat(args[1]));

                    break;

                case "Cure": // TypePlant
                    if (!(monster.getType() instanceof TypePlant)) {
                        throw new Exception("Error configuration : Undefined '" + args[0] + "' attribute for TypePlant::class");
                    }

                    TypePlant plant = (TypePlant) monster.getType();
                    plant.setCure(Float.parseFloat(args[1]));

                    break;
                
                case "Flood": // TypeWater
                    if (!(monster.getType() instanceof TypeWater)) {
                        throw new Exception("Error configuration : Undefined '" + args[0] + "' attribute for TypeWater::class");
                    }

                    TypeWater water = (TypeWater) monster.getType();
                    water.setFlood(Float.parseFloat(args[1]));

                    break;
                
                case "Fall": // TypeWater
                    if (!(monster.getType() instanceof TypeWater)) {
                        throw new Exception("Error configuration : Undefined '" + args[0] + "' attribute for TypeWater::class");
                    }

                    TypeWater water2 = (TypeWater) monster.getType();
                    water2.setFall(Float.parseFloat(args[1]));

                    break;
                
                default:
                    throw new Exception("Error configuration : no attribute '" + args[0] + "' in Type::class");
            }
            return 0;
        }

        switch (args[0]) {
            case "Name":
                monster.setName(args[1]);
                break;

            case "Type":
                // Select the type
                switch (args[1]) {
                    case "Electric":
                        monster.setType(new TypeElectric());
                        break;

                    case "Water":
                        monster.setType(new TypeWater());
                        break;
                    
                    case "Fire":
                        monster.setType(new TypeFire());
                        break;
                    
                    case "Earth":
                        monster.setType(new TypeEarth());
                        break;
                    
                    case "Nature":
                        monster.setType(new TypeNature());
                        break;
                    
                    case "Insect":
                        monster.setType(new TypeInsect());
                        break;
                    
                    case "Plant":
                        monster.setType(new TypePlant());
                        break;
                    
                    default:
                        throw new Exception("Undefined type '" + args[1] + "'");
                }
                break;
            
            case "HP":
                monster.setHp(getRandNumberInInterval(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
                break;
            
            case "Speed":
                monster.setSpeed(getRandNumberInInterval(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
                break;
            
            case "Attack":
                monster.setAttack(getRandNumberInInterval(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
                break;
            
            case "Defense":
                monster.setDefense(getRandNumberInInterval(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
                break;
            
            default:
                throw new Exception("Error configuration : no attribute '" + args[0] + "' in Monster::class");
        }

        return 0;
    }

    public static boolean hasAttribute(Object obj, String attributeName) {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().equals(attributeName.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public int getRandNumberInInterval(int intervalFirst, int intervalLast) {
        if (intervalFirst > intervalLast) {
            throw new IllegalArgumentException("Invalid interval. First value must be less than or equal to the last value.");
        }

        Random random = new Random();

        return random.nextInt((intervalLast - intervalFirst) + 1) + intervalFirst;
    }
}
