package edu.gjasinski.chat;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;

class BotManager {
    private ChatManager chatManager;
    private WeatherManager weatherManager;

    BotManager(ChatManager chatManager) throws Exception{
        this.chatManager = chatManager;
        this.weatherManager = new WeatherManager();
    }

    void readChatMessage(String message) throws Exception{
        switch (message){
            case "What hour is it?":
                sendHourToUser();
                break;
            case "What day is today?":
                sendDayOfWeekToUser();
                break;
            case "What weather is in Cracow?":
                sendWeatherToUser();
                break;
        }

    }

    private void sendWeatherToUser() throws Exception {
        String weatherInformation = weatherManager.getWeather();
        chatManager.broadcastMessage("Chatbot", "Server", weatherInformation);
    }

    private void sendDayOfWeekToUser() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) + 6) % 7 - 1;
        chatManager.broadcastMessage("Chatbot", "Server", "Today is " + DayOfWeek.values()[dayOfWeek].toString().toLowerCase());
    }

    private void sendHourToUser() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        chatManager.broadcastMessage("Chatbot", "Server", simpleDateFormat.format(calendar.getTime()));
    }
}
