package gp.assessments.chat.storage.impl;

import gp.assessments.chat.common.entity.ChatChannelEntity;
import gp.assessments.chat.common.entity.ChatMessageEntity;
import gp.assessments.chat.common.model.ChatChannelModel;
import gp.assessments.chat.storage.ChatChannelStorage;
import io.netty.channel.Channel;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ChatChannelStorageImpl implements ChatChannelStorage {

    private final ConcurrentHashMap<String, ChatChannelModel> chatChannelsMap = new ConcurrentHashMap<>();

    private ChatChannelStorageImpl() {
    }

    public static ChatChannelStorage getInstance() {
        return LoadChatStorage.INSTANCE;
    }

    @Override
    public boolean removeFromChannel(final Channel channel, final String channelName, final String userName) {
        ChatChannelModel chatChannelModel = chatChannelsMap.get(channelName);
        if (Objects.nonNull(chatChannelModel) && chatChannelModel.getUsers().remove(userName)) {
            return chatChannelModel.getGroup().remove(channel);
        }
        return false;
    }

    @Override
    public void addToChannel(Channel channel, String channelName, String userName) {
        chatChannelsMap.putIfAbsent(channelName,
                                    new ChatChannelModel(new ChatChannelEntity(channelName, 10),
                                                         new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE)));

        ChatChannelModel chatChannelModel = chatChannelsMap.get(channelName);
        if (chatChannelModel.getUsers().addIfAbsent(userName)) {

            chatChannelModel.getMessages().stream()
                            .sorted(Comparator.comparing(ChatMessageEntity::getSentDate).reversed())
                            .limit(10)
                            .forEach(message -> channel.write(String.format("[%s]: %s",
                                                                            message.getSenderName(),
                                                                            message.getMessage())));
            channel.flush();

            chatChannelModel.getGroup().add(channel);
        }
    }

    private static class LoadChatStorage {
        static final ChatChannelStorage INSTANCE = new ChatChannelStorageImpl();
    }

}
