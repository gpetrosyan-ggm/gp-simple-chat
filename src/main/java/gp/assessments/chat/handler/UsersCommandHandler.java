package gp.assessments.chat.handler;

import gp.assessments.chat.command.UsersCommand;
import io.netty.channel.ChannelHandlerContext;

public class UsersCommandHandler implements CommandHandler<UsersCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, UsersCommand command) {
        System.out.println("Into users command");
    }

}
