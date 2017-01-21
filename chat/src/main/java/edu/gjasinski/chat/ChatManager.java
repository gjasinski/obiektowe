package edu.gjasinski.chat;

import edu.gjasinski.chat.Entities.Channel;
import edu.gjasinski.chat.Entities.User;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import static j2html.TagCreator.*;

class ChatManager {
    private Repository repository;

    ChatManager(Repository repository){
        this.repository = repository;
    }

    void broadcastMessage(String channelName, String sender, String message) {
        Channel channel = repository.getChannel(channelName);

        channel.getUserMap().values().stream().map(User::getSession).filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                        .put("userMessage", createHtmlMessageFromSender(sender, message))
                        .put("userList", repository.getUserSet())
                        .put("channelList", repository.getChannelSet())
                        .put("channelName", channelName)
                ));
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        });
    }

    private String createHtmlMessageFromSender(String sender, String message) {
        return article().with(
                b(sender + " says:" ),
                p(message),
                span().withClass("timestamp").withText(new SimpleDateFormat("HH:mm:ss").format(new Date()))
        ).render();
    }

    void refreshChannelList(Session user) {
        try{
            user.getRemote().sendString(String.valueOf(new JSONObject()
                    .put("refresh", "channel_list")
                    .put("channelList", repository.getChannelSet())));
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    void refreshChannelListAllUsers(){
        repository.getUserCollection().stream().map(User::getSession)
                .forEach(this::refreshChannelList);
    }

    void sendActiveNameChannel(Session user) {
        String channelName = "";

        for (Channel channel: repository.getChannelCollection()) {
            if(channel.isMember(repository.getUsername(user))){
                channelName = channel.getChannelName();
                break;
            }
        }

        if(channelName.equals("")){
            return;
        }

        try{
            user.getRemote().sendString(String.valueOf(new JSONObject()
                    .put("refresh", "active_channel")
                    .put("channelName", channelName)));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void createUsername(Session session, String username) throws Exception {
        try{
            repository.addUser(session, username);
            refreshChannelList(session);
        }catch (IllegalArgumentException ex){
            session.getRemote().sendString(String.valueOf(new JSONObject().put("error", "username_exists")));
        }
    }
}
