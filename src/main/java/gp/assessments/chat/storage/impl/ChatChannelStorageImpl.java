package gp.assessments.chat.storage.impl;

import gp.assessments.chat.common.entity.ChatChannelEntity;
import gp.assessments.chat.common.entity.ChatMessageEntity;
import gp.assessments.chat.common.model.ChatChannelModel;
import gp.assessments.chat.storage.ChatChannelStorage;
import io.netty.channel.Channel;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatChannelStorageImpl implements ChatChannelStorage {

    private final ConcurrentHashMap<String, ChatChannelModel> chatChannelsMap = new ConcurrentHashMap<>();

    private ChatChannelStorageImpl() {
    }

    public static ChatChannelStorage getInstance() {
        return LoadChatStorage.INSTANCE;
    }

    @Override
    public void removeFromChannel(final Channel channel, final String channelName, final String userName) {
        ChatChannelModel chatChannelModel = chatChannelsMap.get(channelName);
        if (Objects.nonNull(chatChannelModel) && chatChannelModel.getUsers().remove(userName)) {
            chatChannelModel.getGroup().remove(channel);
        }
    }

    @Override
    public void addToChannel(final Channel channel, final String channelName, final String userName) {
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

    @Override
    public void addMessageToChannel(final String channelName, final String userName, final String message) {
        ChatChannelModel chatChannelModel = chatChannelsMap.get(channelName);
        if (Objects.isNull(chatChannelModel)) {
            throw new RuntimeException("Invalid channel name");
        }

        chatChannelModel.getMessages().add(new ChatMessageEntity(message, userName, channelName));
        chatChannelModel.getGroup().writeAndFlush(String.format("[%s]: %s", userName, message));
    }

    @Override
    public Set<String> getChannelUsers(final String channelName) {
        ChatChannelModel chatChannelModel = chatChannelsMap.get(channelName);
        if (Objects.nonNull(chatChannelModel)) {
            return new HashSet<>(chatChannelModel.getUsers());
        }
        return new HashSet<>();
    }

    @Override
    public Set<String> getChannel() {
        return new HashSet<>(chatChannelsMap.keySet());
    }

    private static class LoadChatStorage {
        static final ChatChannelStorage INSTANCE = new ChatChannelStorageImpl();
    }

}
