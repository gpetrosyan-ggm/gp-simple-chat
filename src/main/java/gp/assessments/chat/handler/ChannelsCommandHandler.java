package gp.assessments.chat.handler;

import gp.assessments.chat.command.ChannelsCommand;
import gp.assessments.chat.storage.impl.ChatChannelStorageImpl;
import io.netty.channel.ChannelHandlerContext;

import java.util.Set;

public class ChannelsCommandHandler implements CommandHandler<ChannelsCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, ChannelsCommand command) {
        Set<String> channels = ChatChannelStorageImpl.getInstance().getChannel();
        ctx.writeAndFlush(String.join(", ", channels));

        // TODO check if user logged in or not, if not - send error message
        // TODO Send list of channels.

    }

}
