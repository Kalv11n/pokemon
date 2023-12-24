package printer;

public class ColorPrinter {
    // Colors definitions
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m"; //player
    private static final String ANSI_GREEN = "\u001B[32m";//indication
    private static final String ANSI_YELLOW = "\u001B[33m";//attack
    private static final String ANSI_BLUE = "\u001B[34m";//monster
    private static final String ANSI_PURPLE = "\u001B[35m";//return
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_ORANGE = "\u001B[38;5;208m";//object
    
    public static String red(String sentence) {
        return ANSI_RED + sentence + ANSI_RESET;
    }
    
    public static String blue(String sentence) {
        return ANSI_BLUE + sentence + ANSI_RESET;
    }
    
    public static String green(String sentence) {
        return ANSI_GREEN + sentence + ANSI_RESET;
    }
    
    public static String purple(String sentence) {
        return ANSI_PURPLE + sentence + ANSI_RESET;
    }
    
    public static String cyan(String sentence) {
        return ANSI_CYAN + sentence + ANSI_RESET;
    }
    
    public static String orange(String sentence) {
        return ANSI_ORANGE + sentence + ANSI_RESET;
    }
    
    public static String yellow(String sentence) {
        return ANSI_YELLOW + sentence + ANSI_RESET;
    }
}
