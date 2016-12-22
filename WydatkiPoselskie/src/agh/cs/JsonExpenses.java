package agh.cs;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonExpenses {
    private JSONObject jsonObjectExpenses;
    private Politician politician;
    private ExpensesTitles expensesTitles;

    public JsonExpenses(Politician politician, JSONObject jsonObjectExpenses, ExpensesTitles expensesTitles){
        this.jsonObjectExpenses = jsonObjectExpenses;
        this.politician = politician;
        this.expensesTitles = expensesTitles;
    }

    /*
        Question:
        Is it better like this, constructor calls private method updateAllExpenses(),
        or maybe constructor won't call this method but somewhere else i do it.
        example:
        JsonExpense jsonExpenses = new JsonExpenses();
        jsonExpenses.updateAllExpenses();
        or just
        JsonExpense jsonExpenses = new JsonExpenses();
     */

    public void updateAllExpenses(){
        JSONArray jsonArrayPoints = this.jsonObjectExpenses.getJSONArray("punkty");
        JSONArray yearsJsonArray = this.jsonObjectExpenses.getJSONArray("roczniki");

        for(int i = 0; i < yearsJsonArray.length(); i++){
            updateYearExpenses(yearsJsonArray.getJSONObject(i), jsonArrayPoints);
        }
    }

    private void updateYearExpenses(JSONObject expensesFromYearJsonObject, JSONArray pointsJsonArray){
        Expenses expenses = new Expenses(expensesFromYearJsonObject.getInt("rok"), this.expensesTitles);
        JSONArray expensesFromYearJsonArray = expensesFromYearJsonObject.getJSONArray("pola");

        if(expensesFromYearJsonArray.length() != pointsJsonArray.length()){
            //There is minimum one case when expensesFromYearJsonArray.length() != pointsJsonArray.length()
            //json is not valid
            return;
        }

        for(int i = 0; i < expensesFromYearJsonArray.length(); i++){
            expenses.addExpense(pointsJsonArray.getJSONObject(i).getString("tytul"),
                    expensesFromYearJsonArray.getString(i));
        }
        this.politician.addExpenses(expenses);
    }
}
