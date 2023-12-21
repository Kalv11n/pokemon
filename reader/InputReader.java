package reader;

import java.util.Arrays;

import exception.InputErrorException;

public class InputReader {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public boolean checkInterval(int firstChoice, int lastChoice, String choice) {
        try {
            int id = Integer.parseInt(choice); 
            
            if (id < firstChoice || id > lastChoice) 
                throw new InputErrorException(ANSI_RED + "Vous devez choisir un nombre entre " + firstChoice + " et " + lastChoice + ANSI_RESET);

            return true;
        } catch (InputErrorException e) {
            System.err.println(e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.err.println(ANSI_RED + "Vous devez choisir un nombre" + ANSI_RESET);
            return false;
        }
    }

    public boolean checkChoice(String choices, String choice) {
        try {
            if (choice.length() != 1) {
                throw new InputErrorException(ANSI_RED + "Entrez un seul caractère " + ANSI_RESET);
            }
            
            if (!choices.contains(choice.toUpperCase())) 
                throw new InputErrorException(ANSI_RED + "Votre choix n'est pas présent dans les possibilités disponibles" + ANSI_RESET);

            return true;
        } catch (InputErrorException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
