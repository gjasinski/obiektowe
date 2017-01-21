package edu.gjasinski.chat;

import edu.gjasinski.chat.Entities.Channel;
import edu.gjasinski.chat.Entities.User;
import org.eclipse.jetty.websocket.api.Session;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Repository {
    private Map<Session, User> sessionToUser;
    private Map<String, User> usernameToUser;
    private Map<String, Channel> channelMap;
    private int currentUserId;
    private int currentChannelId;

    public Repository(){
        sessionToUser = new ConcurrentHashMap<>();
        usernameToUser = new ConcurrentHashMap<>();
        channelMap = new ConcurrentHashMap<>();
        currentUserId = 0;
        currentChannelId = 0;
    }

    public void addChannel(String channelName){
        if(channelMap.containsKey(channelName)){
            throw new IllegalArgumentException("ChannelManager already exists!");
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
        channel.addUserToChannel(username, usernameToUser.get(username));
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

    public Channel getChannel(String channelName){
        return channelMap.get(channelName);
    }

    public Set<String> getUserSet(){
        return usernameToUser.keySet();
    }

    public Set<String> getChannelSet(){
        return channelMap.keySet();
    }

    public Collection<User> getUserCollection(){
        return usernameToUser.values();
    }
    public Collection<Channel> getChannelCollection(){
        return channelMap.values();
    }

    public Stream<Channel> getUserChannelCollection(Session user) {
        String username = getUsername(user);
        return channelMap.values().stream().filter(channel -> channel.isMember(username));
    }
}
