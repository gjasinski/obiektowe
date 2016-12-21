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

    public BigDecimal getAllExpenses(){
        BigDecimal allExpenses = BigDecimal.ZERO;
        for(Expenses expenses : expensesList){
            allExpenses = allExpenses.add(expenses.getSumOfExpenses());
        }
        return allExpenses;
    }

    @Override
    public String toString(){
        return lastNameFirstName + " " + id;
    }
}
