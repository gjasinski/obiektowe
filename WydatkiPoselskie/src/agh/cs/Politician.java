package agh.cs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzegorz Jasinski on 20.12.16.
 */
public class Politician {
    private int id;
    private String lastNameFirstName;
    private List<BusinessTrip> businessTripList;
    private List<Expenses> expensesList;

    public Politician(int id, String lastNameFirstName){
        this.id = id;
        this.lastNameFirstName = lastNameFirstName;
        expensesList = new ArrayList<>();
        businessTripList = new ArrayList<>();
    }

    public void addBusinessTrip(BusinessTrip businessTrip){
        businessTripList.add(businessTrip);
    }
    public void addExpenses(Expenses expenses){
        this.expensesList.add(expenses);
    }

    public int getId(){
        return this.id;
    }

    public String getLastNameFirstName() {
        return lastNameFirstName;
    }

    public BigDecimal getAllExpenses(){
        BigDecimal allExpenses = BigDecimal.ZERO;
        for(Expenses expenses : expensesList){
            allExpenses = allExpenses.add(expenses.getSumOfExpenses());
        }
        return allExpenses;
    }

    public BigDecimal getAllExpensesForSmallRepairsOfPoliticianOffice(){
        BigDecimal sumOfExpenses = BigDecimal.ZERO;
        for(Expenses expenses : expensesList){
            sumOfExpenses = sumOfExpenses.add(expenses.getExpensesForSmallRepairsOfPoliticianOffice());
        }
        return sumOfExpenses;
    }

    @Override
    public String toString(){
        return lastNameFirstName + " " + id;
    }

    public int getQuantityOfTravels() {
        return this.businessTripList.size();
    }

    public int getLongestTrip() {
        int longestTrip = 0;
        int tripLength;
        for(int i = 0; i < this.businessTripList.size(); i++){
            tripLength = this.businessTripList.get(i).getDurationOfTripInDays();
            if(tripLength > longestTrip){
                longestTrip = tripLength;
            }
        }
        return longestTrip;
    }

    public BigDecimal getCostOfMostExpensiveTrip() {
        BigDecimal costOfMostExpensiveTrip = BigDecimal.ZERO;
        BigDecimal costOfTrip;
        for(int i = 0; i < this.businessTripList.size(); i++){
            costOfTrip = this.businessTripList.get(i).getTotalCost();
            costOfMostExpensiveTrip = costOfMostExpensiveTrip.max(costOfTrip);
        }
        return costOfMostExpensiveTrip;
    }

    public boolean hadBeenInItaly() {
        for(int i = 0; i < this.businessTripList.size(); i++){
            if(this.businessTripList.get(i).thisIsTripToItaly()){
                return true;
            }
        }
        return false;
    }
}
