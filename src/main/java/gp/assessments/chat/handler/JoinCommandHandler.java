package gp.assessments.chat.handler;

import gp.assessments.chat.command.JoinCommand;
import io.netty.channel.ChannelHandlerContext;

public class JoinCommandHandler implements CommandHandler<JoinCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, JoinCommand command) {
        System.out.println("Into join command");
    }

}
