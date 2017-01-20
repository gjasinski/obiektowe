package edu.gjasinski.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;


public class JsonEngine {
    //public JsonEngine(String json){}

    public static void processJson(Session session, String json) throws Exception {
        JSONObject jsonObject = new JSONObject(json);
        String messageType = jsonObject.getString("messageType");
        String message = jsonObject.getString("message");
        System.out.println(jsonObject.toString());
        switch (messageType){
            case "username" :
                UsernameCreator.createUsername(session, message);
                break;

            case "chatMessage":
                Chat.broadcastMessage(jsonObject.getString("channelName"), Chat.getUsername(session), message);
                break;

            case "newChannelName":
                ChannelHandler.createNewChannel(session, message);
                break;

            case "joinToChannelName":
                ChannelHandler.joinToChannel(session, message);
                break;
            case "leaveChannel":
                ChannelHandler.leaveChannel(session, message);
                break;
            default:
                throw new IllegalArgumentException("JsonEngine: " + messageType);
        }
    }
}
