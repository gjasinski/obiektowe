package edu.gjasinski.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

class ChannelManager {
    private ChatManager chatManager;
    private Repository repository;

    ChannelManager(ChatManager chatManager, Repository repository){
        this.chatManager = chatManager;
        this.repository = repository;
    }

    void createNewChannel(Session session, String channelName) throws Exception{
        try{
            repository.addChannel(channelName);
            chatManager.refreshChannelListAllUsers();
        }catch (IllegalArgumentException ex){
            sendMassage(session, "error", "channel_exists");
        }
    }

    void joinToChannel(Session session, String channelName) throws Exception{
        try {
            repository.addUserToChannel(session, channelName);
            chatManager.broadcastMessage(channelName, "Server", repository.getUsername(session) + " joined the chat");
        }catch (IllegalArgumentException ex){
            sendMassage(session, "error", "already_joined");
        }
    }

    void leaveChannel(Session session, String channelName) {
        repository.removeUserFromChannel(session, channelName);
        chatManager.broadcastMessage(channelName, "Server", repository.getUsername(session) + " left the chat");
        chatManager.refreshChannelList(session);
        chatManager.sendActiveNameChannel(session);
    }

    private void sendMassage(Session session, String category, String description) throws Exception {
        session.getRemote().sendString(String.valueOf(new JSONObject().put(category, description)));
    }
}
