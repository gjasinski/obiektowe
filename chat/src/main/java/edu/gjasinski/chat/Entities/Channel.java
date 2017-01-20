package edu.gjasinski.chat.Entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Builder
public class Channel {
    private int id;

    @Getter
    private String channelName;

    @Getter
    @Setter
    private Map<String, User> userMap;

    public void addUserToChanner(String username){

    }

    public boolean isMember(String username) {
        return userMap.containsKey(username);
    }
}
