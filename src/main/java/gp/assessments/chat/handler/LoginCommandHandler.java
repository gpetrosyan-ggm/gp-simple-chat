package gp.assessments.chat.handler;

import gp.assessments.chat.command.LoginCommand;
import gp.assessments.chat.security.AuthenticationManager;
import gp.assessments.chat.storage.impl.TokenStorageImpl;
import io.netty.channel.ChannelHandlerContext;

public class LoginCommandHandler implements CommandHandler<LoginCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, LoginCommand command) {
        String token = new AuthenticationManager().authenticate(command.getName(), command.getPassword());
        TokenStorageImpl.getInstance().save(token);
        ctx.writeAndFlush(String.format("Successfully logged in. Your token is: %s\r\n", token));

        // TODO check if user logged in or not, if yes - send error message
        // TODO If user doesn't exists, created new profile, else login
        // TODO check if user/password is correct or not
        // TODO after login join to last connected channel
    }

}
