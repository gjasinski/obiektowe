package main.java.edu.gjasinski.wydatkiposelskie;

import java.util.HashMap;

public class ExpensesTitles {
    private HashMap<String, Integer> expensesTitles;

    public ExpensesTitles(){
        this.expensesTitles = new HashMap<>();
    }

    public int setExpensesTitleId(String title) {
        if(this.expensesTitles.containsKey(title)){
            return this.expensesTitles.get(title);
        }else{
            int id = this.expensesTitles.size();
            this.expensesTitles.put(title, id);
            return id;
        }
    }

    public int getExpensesTitle(String title) {
        if(this.expensesTitles.containsKey(title)) {
            return this.expensesTitles.get(title);
        }
        else{
            return -1;
        }
    }
}
