package edu.gjasinski.chat;

import static spark.Spark.*;

public class Chat {


    public static void main(String[] args) {
        try {
            ChatHandler chatHandler = new ChatHandler();
            ChannelHandler channelHandler = new ChannelHandler(chatHandler);
            JsonEngine jsonEngine = new JsonEngine(chatHandler, channelHandler);

            chatHandler.addChannel("Chatbot");

            staticFileLocation("/public");
            webSocket("/chat", jsonEngine);
            init();
        }catch (Exception ex){
            System.out.print(ex.toString());
        }
    }


}
