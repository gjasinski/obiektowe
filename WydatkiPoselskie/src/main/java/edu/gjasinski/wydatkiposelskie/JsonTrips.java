package main.java.edu.gjasinski.wydatkiposelskie;

import org.json.JSONArray;

public class JsonTrips {
    private Politician politician;
    private JSONArray tripsJsonArray;

    public JsonTrips(Politician politician, JSONArray tripsJsonArray){
        this.tripsJsonArray = tripsJsonArray;
        this.politician = politician;
    }

    public void addTrips(){
        for(int i = 0; i < this.tripsJsonArray.length(); i++){
            BusinessTrip businessTrip = new BusinessTrip(this.tripsJsonArray.getJSONObject(i));
            politician.addBusinessTrip(businessTrip);
        }
    }
}
