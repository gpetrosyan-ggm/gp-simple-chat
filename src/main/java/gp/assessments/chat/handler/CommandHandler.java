package gp.assessments.chat.handler;

import gp.assessments.chat.command.Command;
import gp.assessments.chat.service.ChatService;
import io.netty.channel.ChannelHandlerContext;

public interface CommandHandler<T extends Command> {

    void handle(ChannelHandlerContext ctx, ChatService chatService, T command);

}
