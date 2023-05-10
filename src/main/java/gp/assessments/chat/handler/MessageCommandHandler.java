package gp.assessments.chat.handler;

import gp.assessments.chat.command.MessageCommand;
import gp.assessments.chat.storage.impl.ChatChannelStorageImpl;
import gp.assessments.chat.utils.CommandUtils;
import gp.assessments.chat.utils.Constants;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageCommandHandler implements CommandHandler<MessageCommand> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void handle(ChannelHandlerContext ctx, MessageCommand command) {
        logger.info("Handling message command...");
        String userName = CommandUtils.getAttributeByNameWithException(ctx, Constants.USER_NAME_ATTR_NAME);
        String channelName = CommandUtils.getAttributeByNameWithException(ctx, Constants.CHANNEL_NAME_ATTR_NAME);
        ChatChannelStorageImpl.getInstance().addMessageToChannel(channelName, userName, command.getMessage());

        // TODO check if user logged in or not, if not - send error message
        // TODO send message to current channel's all connected users

    }

}
