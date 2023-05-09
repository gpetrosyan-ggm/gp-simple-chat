package gp.assessments.chat.handler;

import gp.assessments.chat.command.UsersCommand;
import io.netty.channel.ChannelHandlerContext;

public class UsersCommandHandler implements CommandHandler<UsersCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, UsersCommand command) {

        // TODO check if user logged in or not, if not - send error message
        // TODO Send list of unique users in current channel.

    }

}
