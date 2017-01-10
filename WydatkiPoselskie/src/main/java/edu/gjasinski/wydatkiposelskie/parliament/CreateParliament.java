package main.java.edu.gjasinski.wydatkiposelskie.parliament;


import main.java.edu.gjasinski.wydatkiposelskie.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateParliament{
    private HashMap<Integer, Politician> politicianHashMap;
    private HashMap<String, Integer> politicianLastNameFirstNameHashMap;
    private ExpensesTitles expensesTitles;
    private DownloadManager downloadManager;

    public CreateParliament(String term) throws IOException{
        this.politicianHashMap = new HashMap<>();
        this.politicianLastNameFirstNameHashMap = new HashMap<>();
        this.downloadManager = new DownloadManager();
        this.expensesTitles = new ExpensesTitles();
        URL jsonUrl = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=" + term);

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
        System.out.println("aktywne watki " + Thread.activeCount());
    }

    private void addPoliticiansToList(JSONArray jsonArray){
        JSONObject politicianRecord, politicianDetails;

        for(int i = 0; i < jsonArray.length(); i++){
            politicianRecord = jsonArray.getJSONObject(i);
            politicianDetails = politicianRecord.getJSONObject("data");
            Politician politician = new Politician(politicianRecord.getInt("id"),
                    politicianDetails.getString("poslowie.nazwa_odwrocona"));

            this.politicianHashMap.put(politicianRecord.getInt("id"), politician);
            this.politicianLastNameFirstNameHashMap.put(politician.getLastNameFirstName(), politician.getId());
        }
    }

    public void updatePoliticiansProfile() throws IOException {
        JsonExpenses jsonExpenses;
        JsonTrips jsonTrips;
        JSONObject jsonObject;

        for(Map.Entry<Integer, Politician> entry : this.politicianHashMap.entrySet()){
            String politicianDetails = this.downloadManager.downloadPoliticianTravelsAndExpensesJson(entry.getKey());
            jsonObject = new JSONObject(politicianDetails).getJSONObject("layers");
            jsonExpenses = new JsonExpenses(entry.getValue(), jsonObject.getJSONObject("wydatki"), this.expensesTitles);
            jsonExpenses.updateAllExpenses();

            if(jsonObject.get("wyjazdy") instanceof JSONArray) {
                jsonTrips = new JsonTrips(entry.getValue(), jsonObject.getJSONArray("wyjazdy"));
                jsonTrips.addTrips();
                //When in JSON there is no trips, then this field is empty and it is JSONObject
                //So we ignore this situation.
            }

            // TODO: 22.12.16 Delete after debug
            System.out.print(entry.getKey()+" ");
        }
    }

    public Parliament getParliament(){return new Parliament(this.politicianHashMap, this.politicianLastNameFirstNameHashMap);}

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<Integer, Politician> entry : this.politicianHashMap.entrySet()){
            stringBuilder.append(entry.getValue().toString());
            stringBuilder.append("\n");
        }
        stringBuilder.append("Politicians quantity: ");
        stringBuilder.append(this.politicianHashMap.size());
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
