package gp.assessments.chat.common.model;

import gp.assessments.chat.common.entity.ChatChannelEntity;
import gp.assessments.chat.common.entity.ChatMessageEntity;
import io.netty.channel.group.ChannelGroup;
import lombok.Getter;

import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class ChatChannelModel {

    private final ChatChannelEntity chatChannel;
    private final ChannelGroup group;
    private final CopyOnWriteArrayList<String> users;
    private final CopyOnWriteArrayList<ChatMessageEntity> messages;

    public ChatChannelModel(ChatChannelEntity chatChannel, ChannelGroup group) {
        this.chatChannel = chatChannel;
        this.group = group;
        this.users = new CopyOnWriteArrayList<>();
        this.messages = new CopyOnWriteArrayList<>();
    }

}
