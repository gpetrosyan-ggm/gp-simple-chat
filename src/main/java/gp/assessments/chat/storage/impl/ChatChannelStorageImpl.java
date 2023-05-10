package gp.assessments.chat.storage.impl;

import gp.assessments.chat.common.entity.ChatChannelEntity;
import gp.assessments.chat.common.entity.ChatMessageEntity;
import gp.assessments.chat.common.error.InvalidChannelNameException;
import gp.assessments.chat.common.error.UserLimitExceededException;
import gp.assessments.chat.common.model.ChatChannelModel;
import gp.assessments.chat.storage.ChatChannelStorage;
import gp.assessments.chat.utils.PropertiesUtils;
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
            chatChannelModel.getChannelLimitSemaphore().release();
        }
    }

    @Override
    public void addToChannel(final Channel channel,
                             final String channelName,
                             final String userName,
                             final int channelLimit) {
        chatChannelsMap.putIfAbsent(channelName,
                                    new ChatChannelModel(new ChatChannelEntity(channelName, channelLimit),
                                                         new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE)));

        ChatChannelModel chatChannelModel = chatChannelsMap.get(channelName);
        if (!chatChannelModel.getUsers().contains(userName)) {
            if (chatChannelModel.getChannelLimitSemaphore().tryAcquire()) {
                if (chatChannelModel.getUsers().addIfAbsent(userName)) {
                    chatChannelModel.getGroup().add(channel);
                } else {
                    chatChannelModel.getChannelLimitSemaphore().release();
                }
            } else {
                throw new UserLimitExceededException("User limit exceeded.");
            }
        }

        chatChannelModel.getMessages().stream()
                        .sorted(Comparator.comparing(ChatMessageEntity::getSentDate).reversed())
                        .limit(PropertiesUtils.getAsInt("channel.messages.limit.size"))
                        .forEach(message -> channel.write(createMessageStr(message)));
        channel.flush();
    }

    @Override
    public void addMessageToChannel(final String channelName, final String userName, final String message) {
        ChatChannelModel chatChannelModel = chatChannelsMap.get(channelName);
        if (Objects.isNull(chatChannelModel)) {
            throw new InvalidChannelNameException(String.format("Could not find the channel '%s' in the storage",
                                                                channelName));
        }

        ChatMessageEntity chatMessage = new ChatMessageEntity(message, userName, channelName);
        chatChannelModel.getMessages().add(chatMessage);
        chatChannelModel.getGroup().writeAndFlush(createMessageStr(chatMessage));
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

    private String createMessageStr(ChatMessageEntity message) {
        return String.format("[%s][%s]: %s%s",
                             message.getSentDate(),
                             message.getSenderName(),
                             message.getMessage(),
                             "\r\n");
    }

    private static class LoadChatStorage {
        static final ChatChannelStorage INSTANCE = new ChatChannelStorageImpl();
    }

}
