package gp.assessments.chat.handler;

import gp.assessments.chat.command.LeaveCommand;
import gp.assessments.chat.service.ChatService;
import io.netty.channel.ChannelHandlerContext;

public class LeaveCommandHandler implements CommandHandler<LeaveCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, ChatService chatService, LeaveCommand command) {

        // TODO check if user logged in or not, if not - send error message
        // TODO Leave current channel.

    }

}
