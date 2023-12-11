package parser;

public class ConfigParser {
    public String[] parse(String line) {
        return this.splitArguments(line);
        
    }

    public String[] splitArguments(String line) {
        // Split each arguments of the command
        String[] args = line.split("\\s+");
        return args;
    }

}
