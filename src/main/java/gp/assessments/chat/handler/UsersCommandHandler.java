package gp.assessments.chat.handler;

import gp.assessments.chat.command.UsersCommand;
import gp.assessments.chat.storage.impl.ChatChannelStorageImpl;
import gp.assessments.chat.utils.CommandUtils;
import gp.assessments.chat.utils.Constants;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class UsersCommandHandler implements CommandHandler<UsersCommand> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void handle(ChannelHandlerContext ctx, UsersCommand command) {
        logger.info("Handling getting users list command...");
        String channelName = CommandUtils.getAttributeByNameWithException(ctx, Constants.CHANNEL_NAME_ATTR_NAME);
        Set<String> users = ChatChannelStorageImpl.getInstance().getChannelUsers(channelName);
        ctx.writeAndFlush(String.join(", ", users));

        // TODO check if user logged in or not, if not - send error message
        // TODO Send list of unique users in current channel.

    }

}
