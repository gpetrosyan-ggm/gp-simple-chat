package gp.assessments.chat.handler;

import gp.assessments.chat.command.DisconnectCommand;
import gp.assessments.chat.service.ChatService;
import io.netty.channel.ChannelHandlerContext;

public class DisconnectCommandHandler implements CommandHandler<DisconnectCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, ChatService chatService, DisconnectCommand command) {

        // TODO check if user logged in or not, if not - send error message
        // TODO Close connection to server.

    }

}
