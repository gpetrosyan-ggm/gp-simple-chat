package gp.assessments.chat.handler;

import gp.assessments.chat.command.MessageCommand;
import io.netty.channel.ChannelHandlerContext;

public class MessageCommandHandler implements CommandHandler<MessageCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, MessageCommand command) {

        // TODO check if user logged in or not, if not - send error message
        // TODO send message to current channel's all connected users

    }

}
