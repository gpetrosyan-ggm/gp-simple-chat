package gp.assessments.chat.chat;

import gp.assessments.chat.common.model.CommandMapperModel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatHandler extends SimpleChannelInboundHandler<CommandMapperModel> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CommandMapperModel model) throws Exception {
        model.getCommandHandler().handle(ctx, model.getCommand());
    }

}
