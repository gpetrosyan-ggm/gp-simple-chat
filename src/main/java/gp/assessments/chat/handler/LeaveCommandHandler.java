package gp.assessments.chat.handler;

import gp.assessments.chat.command.LeaveCommand;
import io.netty.channel.ChannelHandlerContext;

public class LeaveCommandHandler implements CommandHandler<LeaveCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, LeaveCommand command) {

        // TODO check if user logged in or not, if not - send error message
        // TODO Leave current channel.

    }

}
