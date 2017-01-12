package main.java.edu.gjasinski.wydatkiposelskie;

import org.json.JSONObject;
import java.math.BigDecimal;

public class Trip {
    private String tripDescription;
    private String country;
    private int id;
    private int days;
    private BigDecimal totalCost;

    public Trip(JSONObject businessTripJsonObject){
        this.tripDescription = businessTripJsonObject.getString("delegacja");
        this.country = businessTripJsonObject.getString("kraj");
        this.id = businessTripJsonObject.getInt("id");
        this.days = businessTripJsonObject.getInt("liczba_dni");
        this.totalCost = new BigDecimal(businessTripJsonObject.getString("koszt_suma"));
    }

    public int getDurationOfTripInDays(){
        return this.days;
    }

    public BigDecimal getTotalCost(){
        return this.totalCost;
    }

    public boolean thisIsTripToItaly(){
        return this.country.equals("Włochy");
    }
}
