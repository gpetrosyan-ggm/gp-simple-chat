package gp.assessments.chat.handler;

import gp.assessments.chat.command.LeaveCommand;
import gp.assessments.chat.storage.impl.ChatChannelStorageImpl;
import gp.assessments.chat.utils.CommandUtils;
import gp.assessments.chat.utils.Constants;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaveCommandHandler implements CommandHandler<LeaveCommand> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void handle(ChannelHandlerContext ctx, LeaveCommand command) {
        logger.info("Handling leave command...");
        String userName = CommandUtils.getAttributeByNameWithException(ctx, Constants.USER_NAME_ATTR_NAME);
        String channelName = CommandUtils.getAttributeByNameWithException(ctx, Constants.CHANNEL_NAME_ATTR_NAME);

        ChatChannelStorageImpl.getInstance().removeFromChannel(ctx.channel(), channelName, userName);

        ctx.writeAndFlush(String.format("%s user logout from the the channel", userName));
        ctx.channel().close();

        // TODO check if user logged in or not, if not - send error message
        // TODO Leave current channel.
    }

}
