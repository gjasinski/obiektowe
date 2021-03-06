package main.java.edu.gjasinski.wydatkiposelskie;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Politician {
    private int id;
    private String lastNameFirstName;
    private List<Trip> tripList;
    private List<Expenses> expensesList;

    public Politician(int id, String lastNameFirstName) {
        this.id = id;
        this.lastNameFirstName = lastNameFirstName;
        expensesList = new ArrayList<>();
        tripList = new ArrayList<>();
    }

    public void addBusinessTrip(Trip trip) {
        this.tripList.add(trip);
    }

    public void addExpenses(Expenses expenses) {
        this.expensesList.add(expenses);
    }

    public int getId() {
        return this.id;
    }

    public String getLastNameFirstName() {
        return this.lastNameFirstName;
    }

    public BigDecimal getAllExpenses() {
        BigDecimal allExpenses = BigDecimal.ZERO;

        for (Expenses expenses : this.expensesList) {
            allExpenses = allExpenses.add(expenses.getSumOfExpenses());
        }
        return allExpenses;
    }

    public BigDecimal getAllExpensesForSmallRepairsOfPoliticianOffice() {
        BigDecimal sumOfExpenses = BigDecimal.ZERO;

        for (Expenses expenses : this.expensesList) {
            sumOfExpenses = sumOfExpenses.add(expenses.getExpensesForSmallRepairsOfPoliticianOffice());
        }
        return sumOfExpenses;
    }

    public int getQuantityOfTips() {
        return this.tripList.size();
    }

    public int getDurationOfTrips() {
        int durationOfTrips = 0;
        for(Trip trip : this.tripList){
            durationOfTrips += trip.getDurationOfTripInDays();
        }
        return  durationOfTrips;
    }

    public BigDecimal getCostOfMostExpensiveTrip() {
        BigDecimal costOfMostExpensiveTrip = BigDecimal.ZERO;
        BigDecimal costOfTrip;

        for (Trip tripList : this.tripList) {
            costOfTrip = tripList.getTotalCost();
            costOfMostExpensiveTrip = costOfMostExpensiveTrip.max(costOfTrip);
        }
        return costOfMostExpensiveTrip;
    }

    public boolean hadBeenInItaly() {
        for (Trip aBusinessTripList : this.tripList) {
            if (aBusinessTripList.thisIsTripToItaly()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.id + " " + this.lastNameFirstName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Politician)) {
            return false;
        }
        return this.id == ((Politician) other).getId();
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}