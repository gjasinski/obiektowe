package agh.cs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Grzegorz Jasinski on 17.12.16.
 */
public class JsonPoliticians {
    private JSONObject jsonLinks, json;
    private JSONArray jsonDataArray;

    public JsonPoliticians(String jsonData){
        this.json = new JSONObject(jsonData);
        this.jsonDataArray = this.json.getJSONArray("Dataobject");
        this.jsonLinks = this.json.getJSONObject("Links");
    }

    public boolean hasNext(){
        //return false;
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

    public JSONArray getJsonDataArray(){
        return this.jsonDataArray;
    }
}
