package main.java.edu.gjasinski.wydatkiposelskie;


import main.java.edu.gjasinski.wydatkiposelskie.parliament.CreateParliament;
import main.java.edu.gjasinski.wydatkiposelskie.parliament.Parliament;
import main.java.edu.gjasinski.wydatkiposelskie.rubbish.GenerateAndWriteAllParliamentInfo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PoliticsSystem {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        InputParser inputParser = new InputParser();
        try{
            inputParser.argumentsAreValid(args);
            CreateParliament createParliament = new CreateParliament(args[1]);
            //createParliament.updatePoliticiansProfile();
            Parliament parliament = createParliament.getParliament();
            String computedInformation = "";

            switch (Integer.parseInt(args[0])){
                case 0: printHelp();
                    break;
                case 1: printListOfPoliticians(parliament.getListOfPoliticians());
                    break;
                case 2: printListOfPoliticians(parliament.getListOfPoliticiansInAlphabeticOrder());
                    break;
                case 3: computedInformation = parliament.getPoliticiansAverageSumOfAllExpenses().toString();
                    break;
                case 4: computedInformation = parliament.getPoliticianIdWhoTravelsMost().toString();
                    break;
                case 5: computedInformation = parliament.getPoliticianIdWithTheLongestTrip().toString();
                    break;
                case 6: computedInformation = parliament.getPoliticianIdWithTheMostExpensiveTrip().toString();
                    break;
                case 7: printListOfPoliticians(parliament.getListOfPoliticiansWhoHadBeenInItaly());
                    break;
                case 8: computedInformation = parliament.getPoliticianSumOfAllExpenses(args[1] + " " + args[3]).toString();
                    break;
                case 9: computedInformation = parliament.getPoliticianAllExpensesForSmallRepairsOfPoliticianOffice(args[1]
                        + " " + args[3]).toString();
                    break;
                case 10: new GenerateAndWriteAllParliamentInfo(parliament);
                    break;
            }

            System.out.println(computedInformation);

        }
        catch (IOException | IllegalArgumentException e){
            System.out.println("Something went wrong:\n" + e.toString());
        }
        catch (Exception e){
            System.out.println("Something strange went wrong:\n" + e.toString());
        }
        System.out.print((System.currentTimeMillis()-timeStart)/1000);
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
