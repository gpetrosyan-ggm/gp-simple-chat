package gp.assessments.chat.handler;

import gp.assessments.chat.command.ChannelsCommand;
import gp.assessments.chat.storage.impl.ChatChannelStorageImpl;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChannelsCommandTest {

    @InjectMocks
    private ChannelsCommandHandler commandHandler;

    @Mock
    private ChannelHandlerContext ctx;

    @Test
    @DisplayName("Channels command handler")
    void givenChannelCommand_whenChanelCommandHandle_thenWriteChannelsInContext() {
        try (MockedStatic<ChatChannelStorageImpl> chatChannelStorageStatic = Mockito.mockStatic(ChatChannelStorageImpl.class)) {
            ChatChannelStorageImpl chatChannelStorageMock = mock(ChatChannelStorageImpl.class);
            chatChannelStorageStatic.when(ChatChannelStorageImpl::getInstance).thenReturn(chatChannelStorageMock);

            when(chatChannelStorageMock.getChannel()).thenReturn(new HashSet<>());

            commandHandler.handle(ctx, new ChannelsCommand());
        }
    }

}
