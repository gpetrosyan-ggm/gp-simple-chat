package gp.assessments.chat.handler;

import gp.assessments.chat.command.JoinCommand;
import gp.assessments.chat.service.ChatService;
import io.netty.channel.ChannelHandlerContext;

public class JoinCommandHandler implements CommandHandler<JoinCommand> {

    @Override
    public void handle(ChannelHandlerContext ctx, ChatService chatService, JoinCommand command) {

        // TODO check if user logged in or not, if not - send error message
        // TODO check if user already joined to this channel , is yes - send error message
        // TODO check if user has another joined channel, is yes - leave it out
        // TODO check if client's limit exceeded, if yes - send error message
        // TODO otherwise join channel and send last N messages

    }

}
