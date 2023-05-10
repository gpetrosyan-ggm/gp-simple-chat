package gp.assessments.chat.handler;

import gp.assessments.chat.command.JoinCommand;
import gp.assessments.chat.common.entity.ChatChannelEntity;
import gp.assessments.chat.storage.impl.ChatChannelStorageImpl;
import gp.assessments.chat.utils.CommandUtils;
import gp.assessments.chat.utils.Constants;
import io.netty.channel.ChannelHandlerContext;

import java.util.Optional;

public class JoinCommandHandler implements CommandHandler<JoinCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, JoinCommand command) {
        String userName = CommandUtils.getAttributeByNameWithException(ctx, Constants.USER_NAME_ATTR_NAME);
        Optional<String> joinedChannelNameOpt = CommandUtils.getAttributeByName(ctx, Constants.CHANNEL_NAME_ATTR_NAME);

        if (joinedChannelNameOpt.isPresent()) {
            String joinedChannelName = joinedChannelNameOpt.get();
            if (joinedChannelName.equals(command.getChannelName())) {
                throw new RuntimeException("User already joined this channel");
            }

            ChatChannelStorageImpl.getInstance().removeFromChannel(ctx.channel(), joinedChannelName, userName);
        }

        ChatChannelStorageImpl.getInstance().addToChannel(ctx.channel(), command.getChannelName(), userName);

        CommandUtils.setAttributeByName(ctx, Constants.CHANNEL_NAME_ATTR_NAME, command.getChannelName());
        ctx.writeAndFlush(String.format("%s user joined to the the channel", userName));

        // TODO check if user logged in or not, if not - send error message
        // TODO check if user already joined to this channel , is yes - send error message
        // TODO check if user has another joined channel, is yes - leave it out
        // TODO check if client's limit exceeded, if yes - send error message
        // TODO otherwise join channel and send last N messages

    }

}
