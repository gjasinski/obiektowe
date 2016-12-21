package agh.cs;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by Grzegorz Jasinski on 20.12.16.
 */
public class Expenses {
    private HashMap<Integer, BigDecimal> expensesMap;
    private int year;
    private BigDecimal sumOfExpenses;
    private ExpensesTitles expensesTitles;

    public Expenses(int year, ExpensesTitles expensesTitles){
        this.expensesMap = new HashMap<>();
        this.year = year;
        this.sumOfExpenses = BigDecimal.ZERO;
        this.expensesTitles = expensesTitles;
    }

    public void addExpense(String title, String value) {
        int id = this.expensesTitles.getOrSetExpenseTitleId(title);
        if (this.expensesMap.containsKey(id)) {
            this.sumOfExpenses = this.sumOfExpenses.subtract(this.expensesMap.get(id));
        }
        BigDecimal bigDecimal = new BigDecimal(value);
        this.expensesMap.put(id, bigDecimal);
        this.sumOfExpenses = this.sumOfExpenses.add(bigDecimal);
    }

    public BigDecimal getSumOfExpenses(){
        return this.sumOfExpenses;
    }
}
