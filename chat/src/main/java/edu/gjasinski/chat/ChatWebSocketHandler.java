package edu.gjasinski.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ChatWebSocketHandler {
    private String sender, msg;

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
        //String username = "User" ;
        //Chat.usernameMap.put(user, username);
        //String username = Chat.usernameMap.get(user);
        //Chat.broadcastMessage(sender = "Server", msg = (username + " joined the chat"));
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        try {
            Chat.removeUser(user);
            //Chat.broadcastMessage(sender = "Server", msg = (username + " left the chat"));
            // TODO: 15.01.17 add message of leaving chat
        }catch (IllegalArgumentException ex){
            System.out.println("OnWebSocketClose - " + ex.toString());
        }
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message){
        //Chat.broadcastMessage(sender = Chat.usernameMap.get(user), msg = message);
        try{
            JsonEngine.processJson(user, message);
        }
        catch (Exception ex){
            System.out.print(ex.toString());
        }
    }
}