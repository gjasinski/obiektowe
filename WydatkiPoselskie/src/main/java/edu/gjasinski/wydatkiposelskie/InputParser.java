package main.java.edu.gjasinski.wydatkiposelskie;

 class InputParser {
    InputParser(){}

    boolean argumentsAreValid(String[] args) throws IllegalArgumentException{
        try {
            if(args.length != 2 && args.length != 4){
                throw new IllegalArgumentException("Not enough/too many arguments or arguments are invalid. Number " +
                        "of arguments: " + args.length);
            }

            if(Integer.parseInt(args[1]) < 1){
                throw new IllegalArgumentException("Second argument is invalid. Term can't be negative.");
            }

            if (args.length == 2 && Integer.parseInt(args[0]) >= 0 && Integer.parseInt(args[0]) <= 7 ) {
                return true;
            }

            if (args.length == 4 && Integer.parseInt(args[0]) > 7 && Integer.parseInt(args[0]) < 10) {
                return true;
            }
        }
        catch(NumberFormatException ex){
            throw new IllegalArgumentException("Invalid arguments. Type 0 to get more information.", ex);
        }

        throw new IllegalArgumentException("Parser. Invalid argument combination");
    }
}