package edu.gjasinski.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONException;
import org.json.JSONObject;

public class ChannelHandler {
    private ChatHandler chatHandler;

    public ChannelHandler(ChatHandler chatHandler){
        this.chatHandler = chatHandler;
    }

    public void createNewChannel(Session session, String channelName) throws Exception{
        try{
            chatHandler.addChannel(channelName);
            chatHandler.refreshChannelListAllUsers();
        }catch (IllegalArgumentException ex){
            sendMassage(session, "error", "channel_exists");
        }
    }

    public void joinToChannel(Session session, String channelName) throws Exception{
        try {
            chatHandler.addUserToChannel(session, channelName);
            chatHandler.broadcastMessage(channelName, "Server", chatHandler.getUsername(session) + " joined the chat");
        }catch (IllegalArgumentException ex){
            sendMassage(session, "error", "already_joined");
        }
    }

    public void leaveChannel(Session session, String channelName) {
        chatHandler.removeUserFromChannel(session, channelName);
        chatHandler.broadcastMessage(channelName, "Server", chatHandler.getUsername(session) + " left the chat");
        chatHandler.refreshChannelList(session);
        chatHandler.sendActiveNameChannel(session);
    }


    private void sendMassage(Session session, String category, String description) throws Exception {
        session.getRemote().sendString(String.valueOf(new JSONObject().put(category, description)));
    }


}
