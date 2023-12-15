import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import parser.ConfigParser;
import reader.ConfigReader;

class Main {
    public static void main(String[] args) throws Exception {
        ConfigParser parser = new ConfigParser();
        ConfigReader reader = new ConfigReader(parser);
        Explorer explorer = new Explorer();
        reader.read("monster");
        explorer.run();
    }

    
}