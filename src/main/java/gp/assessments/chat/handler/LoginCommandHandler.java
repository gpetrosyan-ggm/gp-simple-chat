package gp.assessments.chat.handler;

import gp.assessments.chat.command.JoinCommand;
import gp.assessments.chat.command.LoginCommand;
import gp.assessments.chat.common.entity.UserEntity;
import gp.assessments.chat.security.AuthenticationManager;
import gp.assessments.chat.storage.impl.UserStorageImpl;
import gp.assessments.chat.utils.CommandUtils;
import gp.assessments.chat.utils.Constants;
import gp.assessments.chat.utils.StringUtils;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Login command handler
 * If the user doesn't exist, create profile else login,
 * after login join to last connected channel
 */
public class LoginCommandHandler implements CommandHandler<LoginCommand> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void handle(ChannelHandlerContext ctx, LoginCommand command) {
        logger.info("Handling login command...");
        String token = new AuthenticationManager().authenticate(command.getUserName(), command.getPassword());
        CommandUtils.setAttributeByName(ctx, Constants.TOKEN_ATTR_NAME, token);
        CommandUtils.setAttributeByName(ctx, Constants.USER_NAME_ATTR_NAME, command.getUserName());
        ctx.writeAndFlush(String.format("Successfully logged in. Your token is: %s%s", token, "\r\n"));

        Optional<UserEntity> userOpt = UserStorageImpl.getInstance().findByUserName(command.getUserName());

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            if (!StringUtils.isBlank(user.getLastChannelName())) {
                JoinCommand joinCommand = new JoinCommand();
                joinCommand.init(new String[]{user.getLastChannelName()});
                new JoinCommandHandler().handle(ctx, joinCommand);
            }
        }
    }

}
