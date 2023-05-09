package gp.assessments.chat.chat;

import gp.assessments.chat.common.model.CommandMapperModel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatHandler extends SimpleChannelInboundHandler<CommandMapperModel> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CommandMapperModel model) throws Exception {
        model.getCommandHandler().handle(ctx, model.getCommand());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.writeAndFlush(String.format("Error: %s\r\n", cause.getMessage()));
    }

}
