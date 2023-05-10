package gp.assessments.chat.handler;

import gp.assessments.chat.command.LoginCommand;
import gp.assessments.chat.security.AuthenticationManager;
import gp.assessments.chat.utils.CommandUtils;
import gp.assessments.chat.utils.Constants;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginCommandHandler implements CommandHandler<LoginCommand> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void handle(ChannelHandlerContext ctx, LoginCommand command) {
        logger.info("Handling login command...");
        String token = new AuthenticationManager().authenticate(command.getUserName(), command.getPassword());
        CommandUtils.setAttributeByName(ctx, Constants.TOKEN_ATTR_NAME, token);
        CommandUtils.setAttributeByName(ctx, Constants.USER_NAME_ATTR_NAME, command.getUserName());
        ctx.writeAndFlush(String.format("Successfully logged in. Your token is: %s%s", token, "\r\n"));

        // TODO check if user logged in or not, if yes - send error message
        // TODO If user doesn't exists, created new profile, else login
        // TODO check if user/password is correct or not
        // TODO after login join to last connected channel
    }

}
