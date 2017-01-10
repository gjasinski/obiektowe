package main.java.edu.gjasinski.wydatkiposelskie;

import java.math.BigDecimal;
import java.util.HashMap;

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
        if(year!= 2012 && year!=2013){
            System.out.println("INNY ROK: " + year);
        }
    }

    public void addExpense(String title, String value) {
        int id = this.expensesTitles.setExpensesTitleId(title);

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

    public BigDecimal getExpensesForSmallRepairsOfPoliticianOffice(){
        int expenseId = this.expensesTitles.getExpensesTitle("Koszty drobnych napraw i remontów lokalu biura poselskiego");
        if(expenseId == -1 || !this.expensesMap.containsKey(expenseId)){
            return BigDecimal.ZERO;
        }
        else{
            return this.expensesMap.get(expenseId);
        }
    }
}
