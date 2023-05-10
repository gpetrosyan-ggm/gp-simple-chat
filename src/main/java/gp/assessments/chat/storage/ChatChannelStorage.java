package gp.assessments.chat.storage;

import io.netty.channel.Channel;

import java.util.Set;

public interface ChatChannelStorage {

    boolean removeFromChannel(Channel channel, final String channelName, String userName);

    void addToChannel(Channel channel, String channelName, String userName);

    void addMessageToChannel(String channelName, String userName, String message);

    Set<String> getChannelUsers(final String channelName);

}
