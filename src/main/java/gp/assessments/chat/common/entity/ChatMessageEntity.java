package gp.assessments.chat.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageEntity {

    private String senderName;
    private String message;
    private String channelName;
    private LocalDateTime sentDate;

}
