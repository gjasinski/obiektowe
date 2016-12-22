package agh.cs;

import java.util.HashMap;

public class ExpensesTitles {
    private HashMap<String, Integer> expensesTitles;

    public ExpensesTitles(){
        this.expensesTitles = new HashMap<>();
    }

    public int setExpenseTitleId(String title) {
        if(this.expensesTitles.containsKey(title)){
            return this.expensesTitles.get(title);
        }else{
            int id = this.expensesTitles.size();
            this.expensesTitles.put(title, id);
            return id;
        }
    }

    public int getExpenseTitle(String title) {
        return this.expensesTitles.get(title);
    }
}
