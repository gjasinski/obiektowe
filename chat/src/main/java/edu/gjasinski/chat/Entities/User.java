package edu.gjasinski.chat.Entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.eclipse.jetty.websocket.api.Session;

@Builder
public class User {
    private int id;
    @Getter
    private Session session;
    @Getter
    private String username;
}
