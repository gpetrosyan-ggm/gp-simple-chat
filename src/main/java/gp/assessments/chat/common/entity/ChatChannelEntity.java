package gp.assessments.chat.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatChannelEntity {

    private final String id;
    private String channelName;
    private long channelLimit;

    public ChatChannelEntity(String channelName, long channelLimit) {
        this.id = UUID.randomUUID().toString();
        this.channelName = channelName;
        this.channelLimit = channelLimit;
    }

}
