package gp.assessments.chat.storage;

import io.netty.channel.Channel;

public interface ChatChannelStorage {

    boolean removeFromChannel(Channel channel, final String channelName, String userName);

    void addToChannel(Channel channel, String channelName, String userName);

}
