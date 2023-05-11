package gp.assessments.chat.handler;

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

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersCommandTest {

    @InjectMocks
    private UsersCommandHandler commandHandler;

    @Mock
    private ChannelHandlerContext ctx;

    @Test
    @DisplayName("Users command handler")
    void givenUsersCommand_whenUsersCommandHandle_thenWriteUsersInContext() {
        try (MockedStatic<ChatChannelStorageImpl> chatChannelStorageStatic = Mockito.mockStatic(ChatChannelStorageImpl.class);
             MockedStatic<CommandUtils> commandUtilsStatic = Mockito.mockStatic(CommandUtils.class)) {

            ChatChannelStorageImpl chatChannelStorageMock = mock(ChatChannelStorageImpl.class);
            chatChannelStorageStatic.when(ChatChannelStorageImpl::getInstance).thenReturn(chatChannelStorageMock);
            commandUtilsStatic.when(() -> CommandUtils.getAttributeByNameWithException(ctx,
                                                                                       Constants.CHANNEL_NAME_ATTR_NAME))
                              .thenReturn("channelName");

            when(chatChannelStorageMock.getChannelUsers(anyString())).thenReturn(new HashSet<>());

            commandHandler.handle(ctx, new UsersCommand());
        }
    }

}
