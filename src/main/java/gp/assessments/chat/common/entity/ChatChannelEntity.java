package gp.assessments.chat.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Chat channel entity model
 */
@Getter
@Setter
public class ChatChannelEntity {

    private final String id;
    private String channelName;
    private int channelLimit;

    public ChatChannelEntity(String channelName, int channelLimit) {
        this.id = UUID.randomUUID().toString();
        this.channelName = channelName;
        this.channelLimit = channelLimit;
    }

}
