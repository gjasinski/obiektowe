package agh.cs;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;

/**
 * Created by Grzegorz Jasinski on 16.12.16.
 */
public class JsonManager {
    private JSONArray jsonArray;
    private LinkedHashMap<Integer, String> politicianList;

    public JsonManager(URL jsonUrl) throws IOException{
        DownloadManager downloadManager = new DownloadManager();
        StringBuilder stringBuilder = new StringBuilder();
        String downloadedJsonData, jsonContentData;
        JsonParser jsonParser;

        stringBuilder.append("[");
        do{
            downloadedJsonData = downloadManager.downloadJson(jsonUrl);
            jsonParser = new JsonParser(downloadedJsonData);
            jsonContentData = jsonParser.getJson();
            jsonContentData = jsonContentData.substring(1, jsonContentData.length() - 1);
            stringBuilder.append(jsonContentData);
            if(jsonParser.hasNext()){
                stringBuilder.append(",");
                jsonUrl = jsonParser.getNextUrl();
            }
            System.out.println(jsonUrl.toString());
        }while(jsonParser.hasNext());
        stringBuilder.append("]");
        this.jsonArray = new JSONArray(stringBuilder.toString());
        createPoliticianList();
    }

    private void createPoliticianList(){
        this.politicianList = new LinkedHashMap<>();
        JSONObject politicianRecord, politicianDetails;
        for(int i = 0; i < this.jsonArray.length(); i++){
            politicianRecord = this.jsonArray.getJSONObject(i);
            politicianDetails = politicianRecord.getJSONObject("data");
            this.politicianList.put(politicianRecord.getInt("id"), politicianDetails.getString("poslowie.nazwa_odwrocona"));
        }
    }

    public LinkedHashMap<Integer, String> getPoliticianList(){
        return this.politicianList;
    }
}
