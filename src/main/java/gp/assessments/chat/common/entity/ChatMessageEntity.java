package gp.assessments.chat.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageEntity {

    private String id;
    private String message;
    private LocalDateTime sentDate;

    // Relations corresponding to UserEntity and ChatChannelEntity
    private String senderName;
    private String channelName;

}
