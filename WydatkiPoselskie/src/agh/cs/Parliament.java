package agh.cs;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Parliament {
    private HashMap<Integer, Politician> politicianHashMap;
    private HashMap<String, Integer> politicianLastNameFirstNameHashMap;

    public Parliament(HashMap<Integer, Politician> politicianHashMap,
                      HashMap<String, Integer> politicianLastNameFirstNameHashMap){
        this.politicianHashMap = politicianHashMap;
        this.politicianLastNameFirstNameHashMap = politicianLastNameFirstNameHashMap;
    }

    private int getPoliticianIdByLastNameFirstName(String lastNameFirstName){
        if(this.politicianLastNameFirstNameHashMap.containsKey(lastNameFirstName)){
            return this.politicianLastNameFirstNameHashMap.get(lastNameFirstName);
        }
        else{
            throw new IllegalArgumentException("There is no politician: " + lastNameFirstName);
        }
    }

    private BigDecimal getPoliticianSumOfAllExpenses(int id){
        if(politicianHashMap.containsKey(id)){
            return politicianHashMap.get(id).getAllExpenses();
        }
        else {
            throw new IllegalArgumentException("There is no politician with id: " + id);
        }
    }

    public BigDecimal getPoliticianSumOfAllExpenses(String lastNameFirstName){
        return getPoliticianSumOfAllExpenses(getPoliticianIdByLastNameFirstName(lastNameFirstName));
    }

    private BigDecimal getPoliticianAllExpensesForSmallRepairsOfPoliticianOffice(int id){
        if(politicianHashMap.containsKey(id)){
            return politicianHashMap.get(id).getAllExpensesForSmallRepairsOfPoliticianOffice();
        }
        else {
            throw new IllegalArgumentException("There is no politician with id: " + id);
        }
    }

    public BigDecimal getPoliticianAllExpensesForSmallRepairsOfPoliticianOffice(String lastNameFirstName){
        return getPoliticianAllExpensesForSmallRepairsOfPoliticianOffice(getPoliticianIdByLastNameFirstName(lastNameFirstName));
    }

    public BigDecimal getPoliticiansAverageSumOfAllExpenses(){
        BigDecimal sumOfAllExpenses = BigDecimal.ZERO;
        BigDecimal quantity = new BigDecimal(this.politicianHashMap.size());

        for(Map.Entry<Integer, Politician> entry : this.politicianHashMap.entrySet()){
            sumOfAllExpenses = sumOfAllExpenses.add(entry.getValue().getAllExpenses());
        }
        return sumOfAllExpenses.divide(quantity, 2, 0);
    }

    public int getPoliticianIdWhoTravelsMost(){
        int maximumQuantityOfTrips = 0;
        int politicianId = -1;
        int quantityOfTrips;

        for(Map.Entry<Integer, Politician> entry : this.politicianHashMap.entrySet()){
            quantityOfTrips = entry.getValue().getQuantityOfTravels();
            if(quantityOfTrips > maximumQuantityOfTrips){
                maximumQuantityOfTrips = quantityOfTrips;
                politicianId = entry.getKey();
            }
        }
        // TODO: 22.12.16 do testow tylko usunac
        System.out.println(maximumQuantityOfTrips + this.politicianHashMap.get(politicianId).toString());
        return politicianId;
    }

    public int getPoliticianIdWithTheLongestTrip(){
        int maximumLongestTrip = 0;
        int politicianId = -1;
        int longestTrip;

        for(Map.Entry<Integer, Politician> entry : this.politicianHashMap.entrySet()){
            longestTrip = entry.getValue().getLongestTrip();
            if(longestTrip > maximumLongestTrip){
                maximumLongestTrip = longestTrip;
                politicianId = entry.getKey();
            }
        }
        // TODO: 22.12.16 do testow tylko usunac
        System.out.println(maximumLongestTrip + this.politicianHashMap.get(politicianId).toString());
        return politicianId;
    }

    public int getPoliticianIdWithTheMostExpensiveTrip(){
        BigDecimal maximumMostExpensiveTrip = BigDecimal.ZERO;
        BigDecimal mostExpensiveTrip;
        int politicianId = -1;

        for(Map.Entry<Integer, Politician> entry : this.politicianHashMap.entrySet()){
            mostExpensiveTrip = entry.getValue().getCostOfMostExpensiveTrip();
            if(mostExpensiveTrip.compareTo(maximumMostExpensiveTrip) == 1){
                maximumMostExpensiveTrip = mostExpensiveTrip;
                politicianId = entry.getKey();
            }
        }
        // TODO: 22.12.16 do testow tylko usunac
        System.out.println(maximumMostExpensiveTrip + this.politicianHashMap.get(politicianId).toString());
        return politicianId;
    }

    public List<Integer> getListOfPoliticiansWhoHadBeenInItaly(){
        List<Integer> politicianList = new LinkedList<>();

        for(Map.Entry<Integer, Politician> entry : this.politicianHashMap.entrySet()){
            if(entry.getValue().hadBeenInItaly()){
                politicianList.add(entry.getKey());
            }
        }
        return politicianList;
    }
}