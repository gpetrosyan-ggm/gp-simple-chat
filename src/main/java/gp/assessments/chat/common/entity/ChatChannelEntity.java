package gp.assessments.chat.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatChannelEntity {

    private String id;
    private String channelName;

    // Relations corresponding to UserEntity and ChatMessageEntity
    private List<UserEntity> users;
    private List<ChatMessageEntity> messages;

}
