package gp.assessments.chat.handler;

import gp.assessments.chat.command.JoinCommand;
import gp.assessments.chat.common.error.UserAlreadyJoinedChannelException;
import gp.assessments.chat.storage.impl.ChatChannelStorageImpl;
import gp.assessments.chat.storage.impl.UserStorageImpl;
import gp.assessments.chat.utils.CommandUtils;
import gp.assessments.chat.utils.Constants;
import gp.assessments.chat.utils.PropertiesUtils;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class JoinCommandHandler implements CommandHandler<JoinCommand> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void handle(ChannelHandlerContext ctx, JoinCommand command) {
        logger.info("Handling join command...");
        String userName = CommandUtils.getAttributeByNameWithException(ctx, Constants.USER_NAME_ATTR_NAME);
        Optional<String> joinedChannelNameOpt = CommandUtils.getAttributeByName(ctx, Constants.CHANNEL_NAME_ATTR_NAME);

        if (joinedChannelNameOpt.isPresent()) {
            String joinedChannelName = joinedChannelNameOpt.get();
            if (joinedChannelName.equals(command.getChannelName())) {
                throw new UserAlreadyJoinedChannelException(String.format("User already joined the '%s' channel",
                                                                          joinedChannelName));
            }

            ChatChannelStorageImpl.getInstance().removeFromChannel(ctx.channel(), joinedChannelName, userName);
        }

        ChatChannelStorageImpl.getInstance()
                              .addToChannel(ctx.channel(),
                                            command.getChannelName(),
                                            userName,
                                            PropertiesUtils.getAsInt("channel.limit.size"));
        UserStorageImpl.getInstance().updateLastChannelName(userName, command.getChannelName());
        CommandUtils.setAttributeByName(ctx, Constants.CHANNEL_NAME_ATTR_NAME, command.getChannelName());

        logger.info("The '{}' user successfully joined the '{}' channel.", userName, command.getChannelName());
        ctx.writeAndFlush(PropertiesUtils.getAsString("join.command.success") + "\r\n");
    }

}
