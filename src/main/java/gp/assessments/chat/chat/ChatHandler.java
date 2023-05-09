package gp.assessments.chat.chat;

import gp.assessments.chat.service.AuthService;
import gp.assessments.chat.common.model.CommandMapperModel;
import gp.assessments.chat.service.ChatService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatHandler extends SimpleChannelInboundHandler<CommandMapperModel> {

    private final ChatService chatService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CommandMapperModel model) throws Exception {
        model.getCommandHandler().handle(ctx, chatService, model.getCommand());
    }

}
