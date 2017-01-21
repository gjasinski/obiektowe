package edu.gjasinski.chat;

import static spark.Spark.*;

public class Chat {


    public static void main(String[] args) {
        try {
            Repository repository = new Repository();
            ChatManager chatManager = new ChatManager(repository);
            ChannelManager channelManager = new ChannelManager(chatManager, repository);
            JsonManager jsonManager = new JsonManager(chatManager, channelManager, repository);

            repository.addChannel("Chatbot");

            staticFileLocation("/public");
            webSocket("/chat", jsonManager);
            init();
        }catch (Exception ex){
            System.out.print(ex.toString());
        }
    }


}
