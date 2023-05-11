package gp.assessments.chat.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Chat message entity model
 */
@Getter
@Setter
public class ChatMessageEntity {

    private String id;
    private String message;
    private LocalDateTime sentDate;
    private String senderName;
    private String channelName;

    public ChatMessageEntity(String message, String senderName, String channelName) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.sentDate = LocalDateTime.now();
        this.senderName = senderName;
        this.channelName = channelName;
    }

}
