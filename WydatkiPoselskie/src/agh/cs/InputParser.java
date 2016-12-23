package agh.cs;

/**
 * Created by Grzegorz Jasinski on 19.12.16.
 */
public class InputParser {
    public InputParser(){}

    public boolean argumentsAreValid(String[] args) throws IllegalArgumentException{
        try {
            if (args.length == 1 && Integer.parseInt(args[0]) >= 0 && Integer.parseInt(args[0]) <= 9) {
                return true;
            }

            if (args.length == 3 && Integer.parseInt(args[0]) > 7) {
                return true;
            }
        }
        catch(NumberFormatException ex){
            throw new IllegalArgumentException("Invalid arguments. Type 0 to get more information.", ex);
        }

        throw new IllegalArgumentException("Not enough/too many arguments. Number of arguments: " + args.length);
    }
}