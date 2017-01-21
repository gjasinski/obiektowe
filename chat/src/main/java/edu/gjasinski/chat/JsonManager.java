package edu.gjasinski.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONObject;

@WebSocket
public class JsonManager {
    private ChatManager chatManager;
    private ChannelManager channelManager;
    private Repository repository;
    private BotManager botManager;

    public JsonManager(ChatManager chatManager, ChannelManager channelManager, Repository repository) throws Exception{
        this.chatManager = chatManager;
        this.channelManager = channelManager;
        this.repository = repository;
        this.botManager = new BotManager(chatManager);
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        try {
            System.out.print("AAAAAA");
            repository.getUserChannelCollection(user).forEach(channel -> chatManager.broadcastMessage(
                    channel.getChannelName(), "Server", repository.getUsername(user) + " left the chat"));
            repository.removeUser(user);
        }catch (IllegalArgumentException ex){
            System.out.println("OnWebSocketClose - " + ex.toString());
        }
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message){
        try{
            processJson(user, message);
        }
        catch (Exception ex){
            System.out.print(ex.toString());
            ex.printStackTrace();
        }
    }


    public void processJson(Session session, String json) throws Exception {
        JSONObject jsonObject = new JSONObject(json);
        String messageType = jsonObject.getString("messageType");
        String message = jsonObject.getString("message");
        System.out.println(jsonObject.toString());
        switch (messageType){
            case "username" :
                chatManager.createUsername(session, message);
                break;

            case "chatMessage":
                chatManager.broadcastMessage(jsonObject.getString("channelName"), repository.getUsername(session), message);
                if(jsonObject.getString("channelName").equals("Chatbot")){
                    botManager.readChatMessage(message);
                }
                break;

            case "newChannelName":
                channelManager.createNewChannel(session, message);
                break;

            case "joinToChannelName":
                channelManager.joinToChannel(session, message);
                break;
            case "leaveChannel":
                channelManager.leaveChannel(session, message);
                break;
            default:
                throw new IllegalArgumentException("JsonManager: " + messageType);
        }
    }
}
