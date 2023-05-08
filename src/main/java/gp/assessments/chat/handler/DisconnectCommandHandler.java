package gp.assessments.chat.handler;

import gp.assessments.chat.command.DisconnectCommand;
import io.netty.channel.ChannelHandlerContext;

public class DisconnectCommandHandler implements CommandHandler<DisconnectCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, DisconnectCommand command) {
        System.out.println("Into disconnect command");
    }

}
