package edu.gjasinski.chat.Entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.text.DecimalFormat;

@Builder
@Getter
@EqualsAndHashCode(exclude = {"descriptionWeather", "temperatureWeather"} )
public class Weather {
    private String city;
    private String descriptionWeather;
    private double temperatureWeather;
    private long weatherTimeStamp;
    private String units;

    @Override
    public String toString(){
        return "Weather in " + city + ": " + descriptionWeather + " and " + new DecimalFormat("#.##").format(temperatureWeather) + " " + units;
    }
}
