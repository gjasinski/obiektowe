package main.java.edu.gjasinski.wydatkiposelskie.parliament;

import main.java.edu.gjasinski.wydatkiposelskie.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class ParallelPoliticiansUpdate implements Runnable{
    private List<Politician> politicianList;
    private DownloadManager downloadManager;
    private ExpensesTitles expensesTitles;

    public ParallelPoliticiansUpdate(List<Politician> politicianList, ExpensesTitles expensesTitles){
        this.politicianList = politicianList;
        this.expensesTitles = expensesTitles;
        downloadManager = new DownloadManager();
        //run();
    }

    @Override
    public void run(){
        System.out.println("CREATED NEW THREAD" +Thread.activeCount());
        updatePoliticiansProfile();
    }

    public void updatePoliticiansProfile(){
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

                // TODO: 22.12.16 Delete after debug
                System.out.print(politician.getId() + " ");
            }
        }
        catch (IOException ex){
            System.out.println(ex.toString());
        }
    }

}
