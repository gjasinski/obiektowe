package main.java.edu.gjasinski.wydatkiposelskie;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonPoliticians {
    private JSONObject jsonLinks, json;
    private JSONArray jsonDataArray;

    public JsonPoliticians(String jsonData){
        this.json = new JSONObject(jsonData);
        this.jsonDataArray = this.json.getJSONArray("Dataobject");
        this.jsonLinks = this.json.getJSONObject("Links");
    }

    public boolean hasNext(){
        return jsonLinks.has("next");
    }

    public URL getNextUrl() throws MalformedURLException{
        if(hasNext()){
            return new URL(jsonLinks.get("next").toString());
        }else{
            throw new MalformedURLException("JSON is broken. There is no next link.");
        }
    }

    public JSONArray getJsonDataArray(){
        return this.jsonDataArray;
    }
}
