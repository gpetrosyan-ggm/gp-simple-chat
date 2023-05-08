package gp.assessments.chat;

import gp.assessments.chat.common.enums.CommandMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatHandler extends SimpleChannelInboundHandler<CommandMapper> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CommandMapper commandMapper) throws Exception {
        commandMapper.getCommandHandler().handle(ctx, commandMapper.getCommand());
    }

}
