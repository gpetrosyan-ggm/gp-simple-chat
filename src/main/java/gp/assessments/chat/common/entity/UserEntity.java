package gp.assessments.chat.common.entity;

import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * User entity model
 */
@Getter
public class UserEntity {

    private final String id;
    private final String userName;
    private final String password;
    private final AtomicBoolean online;
    private String lastChannelName;

    public UserEntity(String userName, String password) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
        this.password = password;
        this.online = new AtomicBoolean(false);
        lastChannelName = null;
    }

    public void setLastChannelName(String lastChannelName) {
        this.lastChannelName = lastChannelName;
    }

    public void makeOnline() {
        online.compareAndSet(false, true);
    }

    public void makeOffline() {
        online.compareAndSet(true, false);
    }

}
