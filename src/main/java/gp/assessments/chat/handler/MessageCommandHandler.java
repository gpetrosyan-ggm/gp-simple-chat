package gp.assessments.chat.handler;

import gp.assessments.chat.command.MessageCommand;
import gp.assessments.chat.storage.impl.ChatChannelStorageImpl;
import gp.assessments.chat.utils.CommandUtils;
import gp.assessments.chat.utils.Constants;
import io.netty.channel.ChannelHandlerContext;

public class MessageCommandHandler implements CommandHandler<MessageCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, MessageCommand command) {
        String userName = CommandUtils.getAttributeByNameWithException(ctx, Constants.USER_NAME_ATTR_NAME);
        String channelName = CommandUtils.getAttributeByNameWithException(ctx, Constants.CHANNEL_NAME_ATTR_NAME);
        ChatChannelStorageImpl.getInstance().addMessageToChannel(channelName, userName, command.getMessage());

        // TODO check if user logged in or not, if not - send error message
        // TODO send message to current channel's all connected users

    }

}
