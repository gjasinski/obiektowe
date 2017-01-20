package edu.gjasinski.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONObject;

@WebSocket
public class JsonEngine {
    private ChatHandler chatHandler;
    private ChannelHandler channelHandler;

    public JsonEngine(ChatHandler chatHandler, ChannelHandler channelHandler){
        this.chatHandler = chatHandler;
        this.channelHandler = channelHandler;
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        try {
            chatHandler.removeUser(user);
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
                chatHandler.broadcastMessage(jsonObject.getString("channelName"), chatHandler.getUsername(session), message);
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
