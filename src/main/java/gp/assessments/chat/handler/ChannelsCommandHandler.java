package gp.assessments.chat.handler;

import gp.assessments.chat.command.ChannelsCommand;
import io.netty.channel.ChannelHandlerContext;

public class ChannelsCommandHandler implements CommandHandler<ChannelsCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, ChannelsCommand command) {

        // TODO check if user logged in or not, if not - send error message
        // TODO Send list of channels.

    }

}
