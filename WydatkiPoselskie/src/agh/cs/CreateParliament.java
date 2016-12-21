package agh.cs;


import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Grzegorz Jasinski on 16.12.16.
 */
public class CreateParliament {
    private HashMap<Integer, Politician> politicianMap;
    private ExpensesTitles expensesTitles;


    public CreateParliament() throws IOException{
        //URL jsonUrl = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8");
        URL jsonUrl = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json");
        expensesTitles = new ExpensesTitles();
        DownloadManager downloadManager = new DownloadManager();
        this.politicianMap = new HashMap<>();
        String downloadedJsonData;
        JsonPoliticians jsonPoliticians;
        JSONArray jsonArray;

        do{
            downloadedJsonData = downloadManager.downloadJson(jsonUrl);
            jsonPoliticians = new JsonPoliticians(downloadedJsonData);
            jsonArray = jsonPoliticians.getJsonDataArray();
            if(jsonPoliticians.hasNext()){
                jsonUrl = jsonPoliticians.getNextUrl();
            }
            addPoliticiansToList(jsonArray);
        }while(jsonPoliticians.hasNext());
    }

    private void addPoliticiansToList(JSONArray jsonArray){
        JSONObject politicianRecord, politicianDetails;

        for(int i = 0; i < jsonArray.length(); i++){
            politicianRecord = jsonArray.getJSONObject(i);
            politicianDetails = politicianRecord.getJSONObject("data");
            Politician politician = new Politician(politicianRecord.getInt("id"), politicianDetails.getString("poslowie.nazwa_odwrocona"));
            this.politicianMap.put(politicianRecord.getInt("id"), politician);
        }
    }

    public void updatePoliticiansProfile() throws IOException {
        DownloadManager downloadManager = new DownloadManager();
        JsonExpenses jsonExpenses;
        JsonTrips jsonTrips;
        JSONObject jsonObject;

        for(Map.Entry<Integer, Politician> entry : this.politicianMap.entrySet()){
            String politicianDetails = downloadManager.downloadPoliticianTravelsAndExpensesJson(entry.getKey());
            jsonObject = new JSONObject(politicianDetails).getJSONObject("layers");
            jsonExpenses = new JsonExpenses(entry.getValue(), jsonObject.getJSONObject("wydatki"), this.expensesTitles);
            jsonExpenses.updateAllExpenses();
            if(jsonObject.get("wyjazdy") instanceof JSONArray) {
                jsonTrips = new JsonTrips(entry.getValue(), jsonObject.getJSONArray("wyjazdy"));
                jsonTrips.addTrips();
                //When in JSON there is no trips, then this field is empty and it is JSONObject
                //So we ignore this situation.
            }
            System.out.print(entry.getKey()+" ");
        }
    }

    public HashMap<Integer, Politician> getParliment(){
        return this.politicianMap;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<Integer, Politician> entry : this.politicianMap.entrySet()){
            stringBuilder.append(entry.getValue().toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString()+" "+this.politicianMap.size();
    }
}
