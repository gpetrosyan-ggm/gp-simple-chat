package gp.assessments.chat.storage;

import io.netty.channel.Channel;

import java.util.Set;

/**
 * Chat channel storage
 */
public interface ChatChannelStorage {

    /**
     * Leave the user from the channel
     *
     * @param channel     Netty channel
     * @param channelName channel name that the user is leaving
     * @param userName    username that leaving the channel
     */
    void removeFromChannel(Channel channel, final String channelName, String userName);

    /**
     * Add the user to the channel
     *
     * @param channel      Netty channel
     * @param channelName  channel name that the user is joining
     * @param userName     username that adding to the channel
     * @param channelLimit the channel user's limit
     */
    void addToChannel(Channel channel, String channelName, String userName, int channelLimit);

    /**
     * Add message to channel
     *
     * @param channelName channel name
     * @param userName    message sender name
     * @param message     message text
     */
    void addMessageToChannel(String channelName, String userName, String message);

    /**
     * Return the channel's users' list
     *
     * @param channelName channel name
     * @return channel's users list
     */
    Set<String> getChannelUsers(final String channelName);

    /**
     * Returns channels list
     *
     * @return channels list which are saved in the storage
     */
    Set<String> getChannel();

}
