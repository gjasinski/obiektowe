package edu.gjasinski.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.*;
import static spark.Spark.*;

public class Chat {
    private static Map<Session, String> usernameMap = new ConcurrentHashMap<>();
    private static Map<String, Integer> channelNameMap = new ConcurrentHashMap<>();
    private static Map<String, Integer> userToChannel = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try {

            staticFileLocation("/public");
            webSocket("/chat", ChatWebSocketHandler.class);
            init();
        }catch (Exception ex){
            System.out.print(ex.toString());
        }
    }

    public static void broadcastMessage(String sender, String message) {
        usernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                        .put("userMessage", createHtmlMessageFromSender(sender, message))
                        .put("userList", usernameMap.values())
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static String createHtmlMessageFromSender(String sender, String message) {
        return article().with(
                b(sender + " says:" ),
                p(message),
                span().withClass("timestamp").withText(new SimpleDateFormat("HH:mm:ss").format(new Date()))
        ).render();
    }

    public static void addChannel(String channelName){
        if(channelNameMap.containsKey(channelName)){
            throw new IllegalArgumentException("Channel already exists!");
        }
        else {
            channelNameMap.put(channelName, channelNameMap.size());
        }
    }

    public static void addUser(Session session, String username){
        if(usernameMap.containsValue(username)){
            throw new IllegalArgumentException("User already exists!");
        }
        else {
            usernameMap.put(session, username);
        }
    }

    public static void addUserToChannel(String username, String channel){
        if(userToChannel.containsKey(username)){
            userToChannel.remove(username);
        }
        userToChannel.put(username, channelNameMap.get(channel));
    }

    public static String getUsername(Session session){
        return usernameMap.get(session);
    }

    public static Integer getChannelId(String channel){
        return channelNameMap.get(channel);
    }

    public static Integer getUserChannelId(String username){
        return userToChannel.get(username);
    }

    public static void removeUser(Session user){
        if(!usernameMap.containsKey(user)){
            throw new IllegalArgumentException("There is no user with this session");
        }

        String username = usernameMap.get(user);
        userToChannel.remove(username);
        usernameMap.remove(user);
    }
}
