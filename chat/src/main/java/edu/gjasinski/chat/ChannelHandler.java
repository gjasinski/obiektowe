package edu.gjasinski.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONException;
import org.json.JSONObject;

public class ChannelHandler {
    private ChatHandler chatHandler;
    private Repository repository;

    public ChannelHandler(ChatHandler chatHandler, Repository repository){
        this.chatHandler = chatHandler;
        this.repository = repository;
    }

    public void createNewChannel(Session session, String channelName) throws Exception{
        try{
            repository.addChannel(channelName);
            chatHandler.refreshChannelListAllUsers();
        }catch (IllegalArgumentException ex){
            sendMassage(session, "error", "channel_exists");
        }
    }

    public void joinToChannel(Session session, String channelName) throws Exception{
        try {
            repository.addUserToChannel(session, channelName);
            chatHandler.broadcastMessage(channelName, "Server", repository.getUsername(session) + " joined the chat");
        }catch (IllegalArgumentException ex){
            sendMassage(session, "error", "already_joined");
        }
    }

    public void leaveChannel(Session session, String channelName) {
        repository.removeUserFromChannel(session, channelName);
        chatHandler.broadcastMessage(channelName, "Server", repository.getUsername(session) + " left the chat");
        chatHandler.refreshChannelList(session);
        chatHandler.sendActiveNameChannel(session);
    }


    private void sendMassage(Session session, String category, String description) throws Exception {
        session.getRemote().sendString(String.valueOf(new JSONObject().put(category, description)));
    }


}
