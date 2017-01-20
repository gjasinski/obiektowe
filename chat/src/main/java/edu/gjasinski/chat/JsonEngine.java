package edu.gjasinski.chat;

import org.apache.tools.ant.taskdefs.Sync;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONObject;

@WebSocket
public class JsonEngine {
    private ChatHandler chatHandler;
    private ChannelHandler channelHandler;
    private Repository repository;

    public JsonEngine(ChatHandler chatHandler, ChannelHandler channelHandler, Repository repository){
        this.chatHandler = chatHandler;
        this.channelHandler = channelHandler;
        this.repository = repository;
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        try {
            System.out.print("AAAAAA");
            repository.getUserChannelCollection(user).forEach(channel -> chatHandler.broadcastMessage(
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
                chatHandler.createUsername(session, message);
                break;

            case "chatMessage":
                chatHandler.broadcastMessage(jsonObject.getString("channelName"), repository.getUsername(session), message);
                break;

            case "newChannelName":
                channelHandler.createNewChannel(session, message);
                break;

            case "joinToChannelName":
                channelHandler.joinToChannel(session, message);
                break;
            case "leaveChannel":
                channelHandler.leaveChannel(session, message);
                break;
            default:
                throw new IllegalArgumentException("JsonEngine: " + messageType);
        }
    }
}
