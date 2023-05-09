package gp.assessments.chat.handler;

import gp.assessments.chat.command.LoginCommand;
import gp.assessments.chat.service.ChatService;
import io.netty.channel.ChannelHandlerContext;

public class LoginCommandHandler implements CommandHandler<LoginCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, ChatService chatService, LoginCommand command) {

        // TODO check if user logged in or not, if yes - send error message
        // TODO If user doesn't exists, created new profile, else login
        // TODO check if user/password is correct or not
        // TODO after login join to last connected channel

    }

}
