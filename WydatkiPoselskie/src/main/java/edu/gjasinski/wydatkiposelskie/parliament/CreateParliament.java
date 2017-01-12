package main.java.edu.gjasinski.wydatkiposelskie.parliament;


import main.java.edu.gjasinski.wydatkiposelskie.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CreateParliament{
    private HashMap<Integer, Politician> politicianHashMap;
    private HashMap<String, Integer> politicianLastNameFirstNameHashMap;
    private ExpensesTitles expensesTitles;
    private DownloadManager downloadManager;

    public CreateParliament(String term) throws IOException, InterruptedException{
        this.politicianHashMap = new HashMap<>();
        this.politicianLastNameFirstNameHashMap = new HashMap<>();
        this.downloadManager = new DownloadManager();
        this.expensesTitles = new ExpensesTitles();
        URL jsonUrl = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=" + term);

        String downloadedJsonData;
        JsonPoliticians jsonPoliticians;
        JSONArray jsonArray;
        List<Politician> politicianList;
        List<Thread> threads=new LinkedList<>();

        do{
            downloadedJsonData = downloadManager.downloadJson(jsonUrl);
            jsonPoliticians = new JsonPoliticians(downloadedJsonData);
            jsonArray = jsonPoliticians.getJsonDataArray();
            if(jsonPoliticians.hasNext()){
                jsonUrl = jsonPoliticians.getNextUrl();
            }
            politicianList = addPoliticiansToList(jsonArray);
            threads.add(new Thread(new ParallelPoliticiansUpdate(politicianList, expensesTitles)));
        }while(jsonPoliticians.hasNext());

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private List<Politician> addPoliticiansToList(JSONArray jsonArray){
        JSONObject politicianRecord, politicianDetails;
        List<Politician> politicianList = new LinkedList<>();

        for(int i = 0; i < jsonArray.length(); i++){
            politicianRecord = jsonArray.getJSONObject(i);
            politicianDetails = politicianRecord.getJSONObject("data");
            Politician politician = new Politician(politicianRecord.getInt("id"),
                    politicianDetails.getString("poslowie.nazwa_odwrocona"));

            this.politicianHashMap.put(politicianRecord.getInt("id"), politician);
            this.politicianLastNameFirstNameHashMap.put(politician.getLastNameFirstName(), politician.getId());
            politicianList.add(politician);
        }
        return politicianList;
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
