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
import static spark.Spark.*;

public class Chat {
    private static Map<Session, User> sessionToUser = new ConcurrentHashMap<>();
    private static Map<String, User> usernameToUser = new ConcurrentHashMap<>();
    private static Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    private static int currentUserId = 0;
    private static int currentChannelId = 0;
    private static int currentChannelToUserId = 0;


    public static void main(String[] args) {
        try {
            addChannel("Chatbot");
            staticFileLocation("/public");
            webSocket("/chat", ChatWebSocketHandler.class);
            init();
        }catch (Exception ex){
            System.out.print(ex.toString());
        }
    }

    public static void broadcastMessage(String channelName, String sender, String message) {
        Channel channel = channelMap.get(channelName);

        channel.getUserMap().values().stream().map(User::getSession).filter(Session::isOpen).forEach(session -> {
            try {
                System.out.println("wysylam wiadomosc");
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
 /*      usernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                        .put("userMessage", createHtmlMessageFromSender(sender, message))
                        .put("userList", usernameMap.values())
                        .put("channelList", channelNameMap.keySet())
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/
    }

    private static String createHtmlMessageFromSender(String sender, String message) {
        return article().with(
                b(sender + " says:" ),
                p(message),
                span().withClass("timestamp").withText(new SimpleDateFormat("HH:mm:ss").format(new Date()))
        ).render();
    }

    public static void addChannel(String channelName){
        if(channelMap.containsKey(channelName)){
            throw new IllegalArgumentException("ChannelHandler already exists!");
        }
        else {
            Channel channel = Channel.builder().id(currentChannelId++).channelName(channelName)
                    .userMap(new ConcurrentHashMap<>()).build();
            channelMap.put(channelName, channel);
        }
    }

    public static void addUser(Session session, String username){
        if(usernameToUser.containsKey(username)){
            throw new IllegalArgumentException("User already exists!");
        }
        else {
            User newUser = User.builder().id(currentUserId++).session(session).username(username).build();
            sessionToUser.put(session, newUser);
            usernameToUser.put(username, newUser);
        }
    }

    public static void addUserToChannel(Session user, String channelName){
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

    public static String getUsername(Session session){
        return sessionToUser.get(session).getUsername();
    }

    public static void removeUser(Session user){
        if(!sessionToUser.containsKey(user)){
            throw new IllegalArgumentException("There is no user with this session");
        }
        String usernameToDelete = sessionToUser.get(user).getUsername();
        sessionToUser.remove(user);
        usernameToUser.remove(usernameToDelete);
        channelMap.values().stream().map(Channel::getUserMap)
                .forEach(stringUserMap -> stringUserMap.remove(usernameToDelete));
    }

    public static void removeUserFromChannel(Session user, String chanelName){
        Channel channel = channelMap.get(chanelName);
        String username = getUsername(user);
        if(!channel.getUserMap().containsKey(username)){
            throw new IllegalArgumentException("This user isn't member of this channel!");
        }
        else {
            channel.getUserMap().remove(username);
        }
    }

    public static void refreshChannelList(Session user) {
        try{
        user.getRemote().sendString(String.valueOf(new JSONObject()
                .put("refresh", "channel_list")
                .put("channelList", channelMap.keySet())));
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
