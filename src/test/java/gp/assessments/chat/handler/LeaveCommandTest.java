package gp.assessments.chat.handler;

import gp.assessments.chat.command.ChannelsCommand;
import gp.assessments.chat.command.LeaveCommand;
import gp.assessments.chat.command.UsersCommand;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeaveCommandTest {

    @InjectMocks
    private LeaveCommandHandler commandHandler;

    @Mock
    private ChannelHandlerContext ctx;

    @Test
    @DisplayName("Leave command handler")
    void givenLeaveCommand_whenLeaveCommandHandle_thenLeaveUserFromChannel() {
        try (MockedStatic<ChatChannelStorageImpl> chatChannelStorageStatic = Mockito.mockStatic(ChatChannelStorageImpl.class);
             MockedStatic<CommandUtils> commandUtilsStatic = Mockito.mockStatic(CommandUtils.class)) {
            ChatChannelStorageImpl chatChannelStorageMock = mock(ChatChannelStorageImpl.class);

            commandUtilsStatic.when(() -> CommandUtils.getAttributeByNameWithException(ctx,
                                                                                       Constants.USER_NAME_ATTR_NAME))
                              .thenReturn("userName");
            commandUtilsStatic.when(() -> CommandUtils.getAttributeByNameWithException(ctx,
                                                                                       Constants.CHANNEL_NAME_ATTR_NAME))
                              .thenReturn("channelName");

            chatChannelStorageStatic.when(ChatChannelStorageImpl::getInstance).thenReturn(chatChannelStorageMock);
            doNothing().when(chatChannelStorageMock).removeFromChannel(any(), anyString(), anyString());


            commandHandler.handle(ctx, new LeaveCommand());
        }
    }

}
