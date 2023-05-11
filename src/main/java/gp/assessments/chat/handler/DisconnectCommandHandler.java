package gp.assessments.chat.handler;

import gp.assessments.chat.command.DisconnectCommand;
import gp.assessments.chat.security.AuthenticationManager;
import gp.assessments.chat.utils.CommandUtils;
import gp.assessments.chat.utils.Constants;
import gp.assessments.chat.utils.PropertiesUtils;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Disconnect command handler
 */
public class DisconnectCommandHandler implements CommandHandler<DisconnectCommand> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void handle(ChannelHandlerContext ctx, DisconnectCommand command) {
        logger.info("Handling disconnect command...");
        Optional<String> userName = CommandUtils.getAttributeByName(ctx, Constants.USER_NAME_ATTR_NAME);
        Optional<String> token = CommandUtils.getAttributeByName(ctx, Constants.TOKEN_ATTR_NAME);

        if (userName.isPresent() && token.isPresent()) {
            new AuthenticationManager().logout(userName.get(), token.get());
            CommandUtils.setAttributeByName(ctx, Constants.USER_NAME_ATTR_NAME, null);
            CommandUtils.setAttributeByName(ctx, Constants.TOKEN_ATTR_NAME, null);
        }

        CommandUtils.setAttributeByName(ctx, Constants.CHANNEL_NAME_ATTR_NAME, null);

        logger.info("The '{}' user successfully disconnected from the chat.", userName);
        ctx.writeAndFlush(PropertiesUtils.getAsString("disconnect.command.success") + "\r\n");
        ctx.channel().close();
    }

}
