package gp.assessments.chat.handler;

import gp.assessments.chat.command.ChannelsCommand;
import gp.assessments.chat.storage.impl.ChatChannelStorageImpl;
import gp.assessments.chat.utils.PropertiesUtils;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Getting channels command handler
 */
public class ChannelsCommandHandler implements CommandHandler<ChannelsCommand> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void handle(ChannelHandlerContext ctx, ChannelsCommand command) {
        logger.info("Handling getting channels list command...");
        Set<String> channels = ChatChannelStorageImpl.getInstance().getChannel();
        ctx.writeAndFlush(
                PropertiesUtils.getAsString("channels.command.success") + String.join(", ", channels) + "\r\n");
    }

}
