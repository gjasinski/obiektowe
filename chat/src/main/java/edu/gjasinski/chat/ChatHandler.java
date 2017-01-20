package edu.gjasinski.chat;

import edu.gjasinski.chat.Entities.Channel;
import edu.gjasinski.chat.Entities.User;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.*;

public class ChatHandler {
    private Map<Session, User> sessionToUser;
    private Map<String, User> usernameToUser;
    private Map<String, Channel> channelMap;

    private int currentUserId;
    private int currentChannelId;

    public ChatHandler(){
        sessionToUser = new ConcurrentHashMap<>();
        usernameToUser = new ConcurrentHashMap<>();
        channelMap = new ConcurrentHashMap<>();

        currentUserId = 0;
        currentChannelId = 0;
    }

    public void broadcastMessage(String channelName, String sender, String message) {
        Channel channel = channelMap.get(channelName);

        channel.getUserMap().values().stream().map(User::getSession).filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                        .put("userMessage", createHtmlMessageFromSender(sender, message))
                        .put("userList", usernameToUser.keySet())
                        .put("channelList", channelMap.keySet())
                        .put("channelName", channelName)
                ));
            } catch (Exception e) {
                e.printStackTrace();
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

    public void addChannel(String channelName){
        if(channelMap.containsKey(channelName)){
            throw new IllegalArgumentException("ChannelHandler already exists!");
        }
        else {
            Channel channel = Channel.builder().id(currentChannelId++).channelName(channelName)
                    .userMap(new ConcurrentHashMap<>()).build();
            channelMap.put(channelName, channel);
        }
    }

    public void addUser(Session session, String username){
        if(usernameToUser.containsKey(username)){
            throw new IllegalArgumentException("User already exists!");
        }
        else {
            User newUser = User.builder().id(currentUserId++).session(session).username(username).build();
            sessionToUser.put(session, newUser);
            usernameToUser.put(username, newUser);
        }
    }

    public void addUserToChannel(Session user, String channelName){
        String username = getUsername(user);
        Channel channel = channelMap.get(channelName);
        Map<String, User> userToChannel = channel.getUserMap();
        if(userToChannel.containsKey(username)){
            throw new IllegalArgumentException("User already joined channel!");
        }
        else{
            userToChannel.put(username, usernameToUser.get(username));
            System.out.println("dodalem nowego czloka");
        }
    }

    public String getUsername(Session session){
        return sessionToUser.get(session).getUsername();
    }

    public void removeUser(Session user){
        if(!sessionToUser.containsKey(user)){
            throw new IllegalArgumentException("There is no user with this session");
        }
        String usernameToDelete = sessionToUser.get(user).getUsername();
        sessionToUser.remove(user);
        usernameToUser.remove(usernameToDelete);
        channelMap.values().stream().map(Channel::getUserMap)
                .forEach(stringUserMap -> stringUserMap.remove(usernameToDelete));
    }

    public void removeUserFromChannel(Session user, String chanelName){
        Channel channel = channelMap.get(chanelName);
        String username = getUsername(user);
        if(!channel.getUserMap().containsKey(username)){
            throw new IllegalArgumentException("This user isn't member of this channel!");
        }
        else {
            channel.getUserMap().remove(username);
        }
    }

    public void refreshChannelList(Session user) {
        try{
            user.getRemote().sendString(String.valueOf(new JSONObject()
                    .put("refresh", "channel_list")
                    .put("channelList", channelMap.keySet())));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshChannelListAllUsers(){
        usernameToUser.values().stream().map(User::getSession)
                .forEach(this::refreshChannelList);
    }

    public void sendActiveNameChannel(Session user) {
        String channelName = "";

        for (Channel channel: channelMap.values()) {
            if(channel.isMember(getUsername(user))){
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
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createUsername(Session session, String username) throws Exception {
        try{
            addUser(session, username);
            refreshChannelList(session);
        }catch (IllegalArgumentException ex){
            session.getRemote().sendString(String.valueOf(new JSONObject().put("error", "username_exists")));
        }
    }

}
