package main.java.edu.gjasinski.wydatkiposelskie.parliament;

import main.java.edu.gjasinski.wydatkiposelskie.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

class ParallelPoliticiansUpdate implements Runnable{
    private List<Politician> politicianList;
    private DownloadManager downloadManager;
    private ExpensesTitles expensesTitles;

    ParallelPoliticiansUpdate(List<Politician> politicianList, ExpensesTitles expensesTitles){
        this.politicianList = politicianList;
        this.expensesTitles = expensesTitles;
        downloadManager = new DownloadManager();
    }

    @Override
    public void run(){
        updatePoliticiansProfile();
    }

    private void updatePoliticiansProfile(){
        JsonExpenses jsonExpenses;
        JsonTrips jsonTrips;
        JSONObject jsonObject;

        try {
            for (Politician politician : politicianList) {
                String politicianDetails = this.downloadManager.downloadPoliticianTravelsAndExpensesJson(politician.getId());
                jsonObject = new JSONObject(politicianDetails).getJSONObject("layers");
                jsonExpenses = new JsonExpenses(politician, jsonObject.getJSONObject("wydatki"), this.expensesTitles);
                jsonExpenses.updateAllExpenses();

                if (jsonObject.get("wyjazdy") instanceof JSONArray) {
                    jsonTrips = new JsonTrips(politician, jsonObject.getJSONArray("wyjazdy"));
                    jsonTrips.addTrips();
                    //When in JSON there is no trips, then this field is empty and it is JSONObject
                    //So we ignore this situation.
                }
            }
        }
        catch (IOException ex){
            System.out.println("Parallel downloading - something is wrong: " + ex.toString());
        }
    }

}
