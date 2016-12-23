package agh.cs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Politician {
    private int id;
    private String lastNameFirstName;
    private List<BusinessTrip> businessTripList;
    private List<Expenses> expensesList;

    public Politician(int id, String lastNameFirstName) {
        this.id = id;
        this.lastNameFirstName = lastNameFirstName;
        expensesList = new ArrayList<>();
        businessTripList = new ArrayList<>();
    }

    public void addBusinessTrip(BusinessTrip businessTrip) {
        this.businessTripList.add(businessTrip);
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

    public int getQuantityOfTravels() {
        return this.businessTripList.size();
    }

    public int getLongestTrip() {
        int longestTrip = 0;
        int tripLength;

        for (BusinessTrip aBusinessTripList : this.businessTripList) {
            tripLength = aBusinessTripList.getDurationOfTripInDays();
            if (tripLength > longestTrip) {
                longestTrip = tripLength;
            }
        }
        return longestTrip;
    }

    public BigDecimal getCostOfMostExpensiveTrip() {
        BigDecimal costOfMostExpensiveTrip = BigDecimal.ZERO;
        BigDecimal costOfTrip;

        for (BusinessTrip aBusinessTripList : this.businessTripList) {
            costOfTrip = aBusinessTripList.getTotalCost();
            costOfMostExpensiveTrip = costOfMostExpensiveTrip.max(costOfTrip);
        }
        return costOfMostExpensiveTrip;
    }

    public boolean hadBeenInItaly() {
        for (BusinessTrip aBusinessTripList : this.businessTripList) {
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