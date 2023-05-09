package gp.assessments.chat.handler;

import gp.assessments.chat.command.ChannelsCommand;
import gp.assessments.chat.service.ChatService;
import io.netty.channel.ChannelHandlerContext;

public class ChannelsCommandHandler implements CommandHandler<ChannelsCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, ChatService chatService, ChannelsCommand command) {

        // TODO check if user logged in or not, if not - send error message
        // TODO Send list of channels.

    }

}
