package reader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
                        
                        break;
                
                    case "EndMonster" :
                        if (!createMonster) {
                            throw new Exception("Monster never created");
                        }

                        createMonster = false;

                        break;

                    default:
                        this.parser.parse(line);
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
}
