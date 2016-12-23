package main.java.edu.gjasinski.wydatkiposelskie;

import java.io.*;
import java.util.stream.Collectors;

public class GenerateAndWriteAllParliamentInfo {
    Parliament parliament;

    public GenerateAndWriteAllParliamentInfo(Parliament parliament) throws IOException{
        this.parliament = parliament;
        generateAggregatedInfo();
        generateSumOfExpensesForSmallRepairsInfo();
        generateSumOfExpensesInfo();
    }

    private void generateAggregatedInfo() throws IOException{
        try(PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("aggregatedInfo.txt")))) {
            //PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream("aggregatedInfo.txt")));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(parliament.getPoliticianIdWhoTravelsMost().toString());
            stringBuilder.append("\n");
            stringBuilder.append(parliament.getPoliticianIdWithTheLongestTrip().toString());
            stringBuilder.append("\n");
            stringBuilder.append(parliament.getPoliticianIdWithTheMostExpensiveTrip().toString());
            stringBuilder.append("\n");
            stringBuilder.append(parliament.getPoliticiansAverageSumOfAllExpenses().toString());
            stringBuilder.append("\n");
            stringBuilder.append(parliament.getListOfPoliticiansWhoHadBeenInItaly().stream()
                    .map(Politician::getLastNameFirstName)
                    .collect(Collectors.joining("\n")));
            printWriter.print(stringBuilder.toString().toCharArray());
        }
    }

    private void generateSumOfExpensesInfo() throws IOException{
        try(PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("sumOfAllExpensesInfo.txt")))){
            printWriter.print(this.parliament.getListOfPoliticians().stream()
                    .map(entry -> entry.getLastNameFirstName() + " - " + entry.getAllExpenses())
                    .collect(Collectors.joining("\n"))
                    .toCharArray());
        }
    }

    private void generateSumOfExpensesForSmallRepairsInfo() throws IOException{
        try(PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("sumOfAllExpensesForSmallRepairsInfo.txt")))){
            printWriter.print(this.parliament.getListOfPoliticians().stream()
                    .map(entry -> entry.getLastNameFirstName() + " - " + entry.getAllExpensesForSmallRepairsOfPoliticianOffice())
                    .collect(Collectors.joining("\n"))
                    .toCharArray());
        }
    }

}