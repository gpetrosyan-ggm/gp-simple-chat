package gp.assessments.chat.chat;

import gp.assessments.chat.ChatApplication;
import gp.assessments.chat.common.error.SimpleChatBaseException;
import gp.assessments.chat.common.model.CommandMapperModel;
import gp.assessments.chat.utils.PropertiesUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@RequiredArgsConstructor
public class ChatHandler extends SimpleChannelInboundHandler<CommandMapperModel> {
    private static final Logger logger = LoggerFactory.getLogger(ChatHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CommandMapperModel model) throws Exception {
        model.getCommandHandler().handle(ctx, model.getCommand());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        SimpleChatBaseException customException = (SimpleChatBaseException) cause.getCause();
        if (Objects.isNull(customException)) {
            customException = (SimpleChatBaseException) cause;
        }
        logger.error("Error type: {}, Error code: {}, Error message: {}",
                     customException.getErrorType(),
                     customException.getErrorType().getErrorCode(),
                     customException.getMessage());
        ctx.writeAndFlush(String.format("%s%s",
                                        PropertiesUtils.getAsString(customException.getErrorType().getErrorCodeProp()),
                                        "\r\n"));
    }

}
