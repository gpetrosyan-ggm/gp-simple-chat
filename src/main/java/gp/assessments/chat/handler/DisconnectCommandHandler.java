package gp.assessments.chat.handler;

import gp.assessments.chat.command.DisconnectCommand;
import gp.assessments.chat.security.AuthenticationManager;
import gp.assessments.chat.utils.CommandUtils;
import gp.assessments.chat.utils.Constants;
import io.netty.channel.ChannelHandlerContext;

import java.util.Optional;

public class DisconnectCommandHandler implements CommandHandler<DisconnectCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, DisconnectCommand command) {
        Optional<String> userName = CommandUtils.getAttributeByName(ctx, Constants.USER_NAME_ATTR_NAME);
        Optional<String> token = CommandUtils.getAttributeByName(ctx, Constants.TOKEN_ATTR_NAME);

        if (userName.isPresent() && token.isPresent()) {
            new AuthenticationManager().logout(userName.get(), token.get());
            CommandUtils.setAttributeByName(ctx, Constants.USER_NAME_ATTR_NAME, null);
            CommandUtils.setAttributeByName(ctx, Constants.TOKEN_ATTR_NAME, null);
        }

        CommandUtils.setAttributeByName(ctx, Constants.CHANNEL_NAME_ATTR_NAME, null);

        ctx.writeAndFlush("Successfully logout.");
        ctx.channel().close();

        // TODO check if user logged in or not, if not - send error message
        // TODO Close connection to server.

    }

}
