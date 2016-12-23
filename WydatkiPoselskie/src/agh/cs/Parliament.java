package agh.cs;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


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

    public Politician getPoliticianIdWhoTravelsMost(){
        int maximumQuantityOfTrips = 0;
        int quantityOfTrips;
        Politician politician = null;

        for(Map.Entry<Integer, Politician> entry : this.politicianHashMap.entrySet()){
            quantityOfTrips = entry.getValue().getQuantityOfTravels();
            if(quantityOfTrips > maximumQuantityOfTrips){
                maximumQuantityOfTrips = quantityOfTrips;
                politician = entry.getValue();
            }
        }
        // TODO: 22.12.16 do testow tylko usunac
        System.out.println(maximumQuantityOfTrips + politician.toString());

        return politician;
    }

    public Politician getPoliticianIdWithTheLongestTrip(){
        int maximumLongestTrip = 0;
        int longestTrip;
        Politician politician = null;


        for(Map.Entry<Integer, Politician> entry : this.politicianHashMap.entrySet()){
            longestTrip = entry.getValue().getLongestTrip();
            if(longestTrip > maximumLongestTrip){
                maximumLongestTrip = longestTrip;
                politician = entry.getValue();
            }
        }
        // TODO: 22.12.16 do testow tylko usunac
        System.out.println(maximumLongestTrip + politician.toString());

        return politician;
    }

    public Politician getPoliticianIdWithTheMostExpensiveTrip(){
        BigDecimal maximumMostExpensiveTrip = BigDecimal.ZERO;
        BigDecimal mostExpensiveTrip;
        Politician politician = null;

        for(Map.Entry<Integer, Politician> entry : this.politicianHashMap.entrySet()){
            mostExpensiveTrip = entry.getValue().getCostOfMostExpensiveTrip();
            if(mostExpensiveTrip.compareTo(maximumMostExpensiveTrip) == 1){
                maximumMostExpensiveTrip = mostExpensiveTrip;
                politician = entry.getValue();
            }
        }
        // TODO: 22.12.16 do testow tylko usunac
        System.out.println(maximumMostExpensiveTrip + politician.toString());

        return politician;
    }

    public List<Politician> getListOfPoliticiansWhoHadBeenInItaly(){
        List<Politician> politicianList = new LinkedList<>();

        politicianList.addAll(this.politicianHashMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().hadBeenInItaly())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList()));

        return politicianList;
    }

    public List<Politician> getListOfPoliticians() {
        return this.politicianHashMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<Politician> getListOfPoliticiansInAlphabeticOrder() {
        return this.politicianHashMap.entrySet().stream()
                .sorted((entry1, entry2) -> {
                    if(entry1.getValue().getLastNameFirstName().equals(entry2.getValue().getLastNameFirstName())){
                        return 0;
                    }
                    return entry1.getValue().getLastNameFirstName().compareToIgnoreCase(entry2.getValue().getLastNameFirstName());
                })
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
                /*.sorted(new Comparator<Map.Entry<Integer, Politician>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Politician> o1, Map.Entry<Integer, Politician> o2) {
                        if(o1.getValue().getLastNameFirstName().equals(o2.getValue().getLastNameFirstName())){
                            return 0;
                        }
                        return o1.getValue().getLastNameFirstName().compareToIgnoreCase(o2.getValue().getLastNameFirstName());
                    }
                })*/
    }
}