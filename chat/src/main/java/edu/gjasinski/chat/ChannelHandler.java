package edu.gjasinski.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONException;
import org.json.JSONObject;

public class ChannelHandler {
    public static void createNewChannel(Session session, String channelName) throws Exception{
        try{
            Chat.addChannel(channelName);
        }catch (IllegalArgumentException ex){
            sendMassage(session, "error", "channel_exists");
        }
    }

    public static void joinToChannel(Session session, String channelName) throws Exception{
        try {
            Chat.addUserToChannel(session, channelName);
            Chat.broadcastMessage(channelName, "Server", Chat.getUsername(session) + " joined the chat");
        }catch (IllegalArgumentException ex){
            sendMassage(session, "error", "already_joined");
        }
    }

/*    public static void leaveChannel(Session session, String channelName){
        try {
            Chat.
        }catch (IllegalArgumentException ex){
            sendMassage(session, "error", "already_joined");
        }
    }*/

    private static void sendMassage(Session session, String category, String description) throws Exception {
        session.getRemote().sendString(String.valueOf(new JSONObject().put(category, description)));
    }
}
