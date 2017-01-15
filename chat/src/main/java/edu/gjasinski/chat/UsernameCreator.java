package edu.gjasinski.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class UsernameCreator {
    public static void createUsername(Session session, String username) throws Exception {
        if(Chat.userUsernameMap.containsValue(username)){
            session.getRemote().sendString(String.valueOf(new JSONObject().put("error", "username_exits")));
        }
        else {
            Chat.userUsernameMap.put(session, username);
            Chat.broadcastMessage("Server: ", username + " joined the chat");
        }
    }
}
