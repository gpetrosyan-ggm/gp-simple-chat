package gp.assessments.chat.handler;

import gp.assessments.chat.command.MessageCommand;
import gp.assessments.chat.storage.impl.ChatChannelStorageImpl;
import gp.assessments.chat.utils.CommandUtils;
import gp.assessments.chat.utils.Constants;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageCommandTest {

    @InjectMocks
    private MessageCommandHandler commandHandler;

    @Mock
    private ChannelHandlerContext ctx;

    @Test
    @DisplayName("Message to channel's users")
    void givenMessageHandler_whenMessageCommandHandle_thenAddMessageToChannel() {
        try (MockedStatic<CommandUtils> commandUtilsStatic = Mockito.mockStatic(CommandUtils.class);
             MockedStatic<ChatChannelStorageImpl> chatChannelStorageStatic = Mockito.mockStatic(ChatChannelStorageImpl.class)) {
            ChatChannelStorageImpl chatChannelStorageMock = mock(ChatChannelStorageImpl.class);
            chatChannelStorageStatic.when(ChatChannelStorageImpl::getInstance).thenReturn(chatChannelStorageMock);

            final String userName = "userName";
            final String channelName = "channelName";

            commandUtilsStatic.when(() -> CommandUtils.getAttributeByNameWithException(ctx,
                                                                                       Constants.USER_NAME_ATTR_NAME))
                              .thenReturn(userName);
            commandUtilsStatic.when(() -> CommandUtils.getAttributeByNameWithException(ctx,
                                                                                       Constants.CHANNEL_NAME_ATTR_NAME))
                              .thenReturn(channelName);

            doNothing().when(chatChannelStorageMock).addMessageToChannel(anyString(), anyString(), anyString());

            MessageCommand command = new MessageCommand();
            command.init(new String[]{"message"});
            commandHandler.handle(ctx, command);
        }
    }

}
