package edu.gjasinski.chat;

import static spark.Spark.*;

public class Chat {


    public static void main(String[] args) {
        try {
            Repository repository = new Repository();
            ChatHandler chatHandler = new ChatHandler(repository);
            ChannelHandler channelHandler = new ChannelHandler(chatHandler, repository);
            JsonEngine jsonEngine = new JsonEngine(chatHandler, channelHandler, repository);

            repository.addChannel("Chatbot");

            staticFileLocation("/public");
            webSocket("/chat", jsonEngine);
            init();
        }catch (Exception ex){
            System.out.print(ex.toString());
        }
    }


}
