package gp.assessments.chat.handler;

import gp.assessments.chat.command.ChannelsCommand;
import gp.assessments.chat.storage.impl.ChatChannelStorageImpl;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class ChannelsCommandHandler implements CommandHandler<ChannelsCommand> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void handle(ChannelHandlerContext ctx, ChannelsCommand command) {
        logger.info("Handling getting channels list command...");
        Set<String> channels = ChatChannelStorageImpl.getInstance().getChannel();
        ctx.writeAndFlush(String.join(", ", channels));

        // TODO check if user logged in or not, if not - send error message
        // TODO Send list of channels.

    }

}
