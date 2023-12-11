package parser;

public class ConfigParser {
    public void parse(String line) {
        String[] args = this.splitArguments(line);
    }

    public String[] splitArguments(String line) {
        // Split each arguments of the command
        String[] args = line.split("\\s+");
        return args;
    }

}
