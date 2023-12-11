import parser.ConfigParser;
import reader.ConfigReader;

class Main {
    public static void main(String[] args) throws Exception {
        ConfigParser parser = new ConfigParser();
        ConfigReader reader = new ConfigReader(parser);

        reader.read("attack");
    }
}