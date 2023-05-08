package gp.assessments.chat.handler;

import gp.assessments.chat.command.LoginCommand;
import io.netty.channel.ChannelHandlerContext;

public class LoginCommandHandler implements CommandHandler<LoginCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, LoginCommand command) {
        System.out.println("Into login command");
    }

}
