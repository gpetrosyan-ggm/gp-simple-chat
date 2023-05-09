package gp.assessments.chat.chat;

import gp.assessments.chat.service.AuthService;
import gp.assessments.chat.service.ChatService;
import gp.assessments.chat.service.impl.AuthServiceImpl;
import gp.assessments.chat.service.impl.ChatServiceImpl;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        AuthService authService = new AuthServiceImpl();
        ChatService chatService = new ChatServiceImpl(authService);

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new ChatCommandDecoder());
        pipeline.addLast(new ChatHandler(chatService));
    }

}
