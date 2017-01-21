package edu.gjasinski.chat.Entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.jetty.websocket.api.Session;

import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

@Builder
public class Channel {
    private int id;

    @Getter
    private String channelName;

    @Getter
    private Map<String, User> userMap;

    public void addUserToChannel(String username, User user){
        if(userMap.containsKey(username)){
            throw new IllegalArgumentException("User already joined channel!");
        }
        else{
            userMap.put(username, user);
        }
    }

    public boolean isMember(String username) {
        return userMap.containsKey(username);
    }
}
