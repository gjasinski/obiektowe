package agh.cs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Grzegorz Jasinski on 17.12.16.
 */
public class JsonParser {
    private JSONObject jsonLinks, jsonData;
    private String jsonContentData;

    public JsonParser(String jsonData){
        this.jsonContentData = extractJsonContent(jsonData);
        this.jsonLinks = new JSONObject(extractJsonLinks(jsonData));
    }

    // TODO: 17.12.16 exceptions - what if found index is "-1"
    private String extractJsonContent(String data){
        return data.substring(data.indexOf("\"Dataobject\":") + 13, data.indexOf(",\"Count\""));
    }

    private String extractJsonLinks(String data){
        return data.substring(data.indexOf("\"Links\":") + 8, data.length()-1);
    }

    public boolean hasNext(){
        return jsonLinks.has("next");
    }

    // TODO: 17.12.16 exception - when !hasNext()
    public URL getNextUrl() throws MalformedURLException{
        if(hasNext()){
            return new URL(jsonLinks.get("next").toString());
        }else{
            throw new MalformedURLException("nie wiem cos dodoac");
        }
    }

    public String getJson(){
        return this.jsonContentData;
    }
}
