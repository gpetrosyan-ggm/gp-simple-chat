package gp.assessments.chat.handler;

import gp.assessments.chat.command.MessageCommand;
import io.netty.channel.ChannelHandlerContext;

public class MessageCommandHandler implements CommandHandler<MessageCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, MessageCommand command) {
        System.out.println("Into message command");
    }

}
