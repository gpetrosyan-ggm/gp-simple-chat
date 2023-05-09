package gp.assessments.chat.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatChannelEntity {

    private String channelName;
    private List<UserEntity> users;
    private List<ChatMessageEntity> messages;

}
