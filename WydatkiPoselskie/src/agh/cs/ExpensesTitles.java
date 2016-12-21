package agh.cs;

import java.util.HashMap;

/**
 * Created by Grzegorz Jasinski on 20.12.16.
 */
public class ExpensesTitles {
    private HashMap<String, Integer> expensesTitles;

    public ExpensesTitles(){
        this.expensesTitles = new HashMap<>();
    }

    public int getOrSetExpenseTitleId(String title) {
        if(this.expensesTitles.containsKey(title)){
            return this.expensesTitles.get(title);
        }else{
            int id = this.expensesTitles.size();
            this.expensesTitles.put(title, id);
            return id;
        }
    }
}
