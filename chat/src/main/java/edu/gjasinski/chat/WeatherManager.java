package edu.gjasinski.chat;

import edu.gjasinski.chat.Entities.Weather;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

class WeatherManager {
    private DownloadManager downloadManager;
    private String jsonURL = "http://api.openweathermap.org/data/2.5/weather?q=Cracow&APPID=";
    private Weather weather;

    WeatherManager() throws Exception{
        downloadManager = new DownloadManager();
        getFreshWeather();
    }

    private void getFreshWeather() throws Exception{
        String jsonWeather = downloadManager.downloadJson(new URL(jsonURL + ApiKey.ApiKey));
        JSONObject jsonObject = new JSONObject(jsonWeather);
        String descriptionWeather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
        Double temperatureWeather = Double.parseDouble(jsonObject.getJSONObject("main").getString("temp")) - 273.15;
        Long weatherTimeStamp = new Date().getTime();
        weather = Weather.builder().city("Cracow").descriptionWeather(descriptionWeather)
                .temperatureWeather(temperatureWeather).weatherTimeStamp(weatherTimeStamp).units("C").build();
    }

    String getWeather() throws Exception{
        if(new Date().getTime() - weather.getWeatherTimeStamp() > 600000){
            getFreshWeather();
        }
        return weather.toString();
    }

}
