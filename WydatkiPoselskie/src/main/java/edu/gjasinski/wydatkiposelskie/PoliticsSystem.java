package main.java.edu.gjasinski.wydatkiposelskie;


import main.java.edu.gjasinski.wydatkiposelskie.parliament.ParliamentBuilder;
import main.java.edu.gjasinski.wydatkiposelskie.parliament.Parliament;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PoliticsSystem {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        InputParser inputParser = new InputParser();
        try{
            inputParser.argumentsAreValid(args);
            if(Integer.parseInt(args[0]) == 0){
                printHelp();
                return;
            }
            ParliamentBuilder parliamentBuilder = new ParliamentBuilder(args[1]);
            Parliament parliament = parliamentBuilder.getParliament();
            if(parliament.getListOfPoliticians().isEmpty()){
                System.out.println("There is no info about politicians");
            }
            else {
                String computedInformation ="";
                switch (Integer.parseInt(args[0])) {
                    case 1:
                        printListOfPoliticians(parliament.getListOfPoliticians());
                        break;
                    case 2:
                        printListOfPoliticians(parliament.getListOfPoliticiansInAlphabeticOrder());
                        break;
                    case 3:
                        computedInformation = parliament.getPoliticiansAverageSumOfAllExpenses().toString();
                        break;
                    case 4:
                        computedInformation = parliament.getPoliticianIdWhoTravelsMost().toString();
                        break;
                    case 5:
                        computedInformation = parliament.getPoliticianIdWithTheLongestTrip().toString();
                        break;
                    case 6:
                        computedInformation = parliament.getPoliticianIdWithTheMostExpensiveTrip().toString();
                        break;
                    case 7:
                        printListOfPoliticians(parliament.getListOfPoliticiansWhoHadBeenInItaly());
                        break;
                    case 8:
                        computedInformation = parliament.getPoliticianSumOfAllExpenses(args[2] + " " + args[3]).toString();
                        break;
                    case 9:
                        computedInformation = parliament.getPoliticianAllExpensesForSmallRepairsOfPoliticianOffice(args[2]
                                + " " + args[3]).toString();
                        break;
                    }
                System.out.println(computedInformation);
                }

        }
        catch (IOException | IllegalArgumentException | InterruptedException e){
            System.out.println("Something went wrong:\n" + e.toString());
        }
        catch (Exception e){
            System.out.println("Something strange went wrong:\n" + e.toString());
        }
        System.out.print((System.currentTimeMillis()-timeStart)/1000 + " s");
    }

    private static void printListOfPoliticians(List<Politician> listOfPoliticiansWhoHadBeenInItaly) {
        System.out.println(listOfPoliticiansWhoHadBeenInItaly.stream()
                .map(Politician::toString)
                .collect(Collectors.joining("\n")));
    }

    private static void printHelp() {
        System.out.println("Help");
    }


}
