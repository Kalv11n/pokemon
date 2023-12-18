package reader;

import exception.InputErrorException;

public class InputReader {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public boolean checkInterval(int firstChoice, int lastChoice, String choice) {
        try {
            int id = Integer.parseInt(choice); 
            
            if (id < firstChoice || id > lastChoice) 
                throw new InputErrorException(ANSI_RED + "Your choice must be between " + firstChoice + " and " + lastChoice + ANSI_RESET);

            return true;
        } catch (InputErrorException e) {
            System.err.println(e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.err.println(ANSI_RED + "You have to select a number" + ANSI_RESET);
            return false;
        }
    }
}
