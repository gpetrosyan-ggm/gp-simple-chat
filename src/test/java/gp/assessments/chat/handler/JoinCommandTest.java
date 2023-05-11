package gp.assessments.chat.handler;

import gp.assessments.chat.command.JoinCommand;
import gp.assessments.chat.command.LeaveCommand;
import gp.assessments.chat.common.error.UserAlreadyJoinedChannelException;
import gp.assessments.chat.common.error.UserIncorrectPasswordException;
import gp.assessments.chat.storage.impl.ChatChannelStorageImpl;
import gp.assessments.chat.storage.impl.UserStorageImpl;
import gp.assessments.chat.utils.CommandUtils;
import gp.assessments.chat.utils.Constants;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JoinCommandTest {

    @InjectMocks
    private JoinCommandHandler commandHandler;

    @Mock
    private ChannelHandlerContext ctx;

    @Test
    @DisplayName("Join command handler, when first time joins to the channel")
    void givenFirstTimeJoinCommand_whenUsersCommandHandle_thenJoinToChannel() {
        final String userName = "userName";
        final String channelName = "channelName";

        try (MockedStatic<ChatChannelStorageImpl> chatChannelStorageStatic = Mockito.mockStatic(ChatChannelStorageImpl.class);
             MockedStatic<CommandUtils> commandUtilsStatic = Mockito.mockStatic(CommandUtils.class);
             MockedStatic<UserStorageImpl> userStorageImplStatic = Mockito.mockStatic(UserStorageImpl.class)) {
            ChatChannelStorageImpl chatChannelStorageMock = mock(ChatChannelStorageImpl.class);
            UserStorageImpl userStorageImplMock = mock(UserStorageImpl.class);

            commandUtilsStatic.when(() -> CommandUtils.getAttributeByNameWithException(ctx,
                                                                                       Constants.USER_NAME_ATTR_NAME))
                              .thenReturn(userName);
            commandUtilsStatic.when(() -> CommandUtils.getAttributeByName(ctx,
                                                                          Constants.CHANNEL_NAME_ATTR_NAME))
                              .thenReturn(Optional.empty());

            doReturn(mock(Channel.class)).when(ctx).channel();
            chatChannelStorageStatic.when(ChatChannelStorageImpl::getInstance).thenReturn(chatChannelStorageMock);
            doNothing().when(chatChannelStorageMock)
                       .addToChannel(any(Channel.class), anyString(), anyString(), anyInt());

            userStorageImplStatic.when(UserStorageImpl::getInstance).thenReturn(userStorageImplMock);
            doNothing().when(userStorageImplMock).updateLastChannelName(anyString(), anyString());

            JoinCommand command = new JoinCommand();
            command.init(new String[]{channelName});
            commandHandler.handle(ctx, command);
        }
    }

    @Test
    @DisplayName("Join command handler, when joins to the another channel")
    void givenAnotherChannelJoinCommand_whenUsersCommandHandle_thenJoinToChannel() {
        final String userName = "userName";
        final String channelName = "channelName";
        final String joinedChannelName = "joinedChannelName";

        try (MockedStatic<ChatChannelStorageImpl> chatChannelStorageStatic = Mockito.mockStatic(ChatChannelStorageImpl.class);
             MockedStatic<CommandUtils> commandUtilsStatic = Mockito.mockStatic(CommandUtils.class);
             MockedStatic<UserStorageImpl> userStorageImplStatic = Mockito.mockStatic(UserStorageImpl.class)) {
            ChatChannelStorageImpl chatChannelStorageMock = mock(ChatChannelStorageImpl.class);
            UserStorageImpl userStorageImplMock = mock(UserStorageImpl.class);

            commandUtilsStatic.when(() -> CommandUtils.getAttributeByNameWithException(ctx,
                                                                                       Constants.USER_NAME_ATTR_NAME))
                              .thenReturn(userName);
            commandUtilsStatic.when(() -> CommandUtils.getAttributeByName(ctx,
                                                                          Constants.CHANNEL_NAME_ATTR_NAME))
                              .thenReturn(Optional.of(joinedChannelName));

            doReturn(mock(Channel.class)).when(ctx).channel();
            chatChannelStorageStatic.when(ChatChannelStorageImpl::getInstance).thenReturn(chatChannelStorageMock);
            doNothing().when(chatChannelStorageMock)
                       .removeFromChannel(any(Channel.class), anyString(), anyString());
            doNothing().when(chatChannelStorageMock)
                       .addToChannel(any(Channel.class), anyString(), anyString(), anyInt());

            userStorageImplStatic.when(UserStorageImpl::getInstance).thenReturn(userStorageImplMock);
            doNothing().when(userStorageImplMock).updateLastChannelName(anyString(), anyString());

            JoinCommand command = new JoinCommand();
            command.init(new String[]{channelName});
            commandHandler.handle(ctx, command);
        }
    }

    @Test
    @DisplayName("Join command handler, when joins to the same channel")
    void givenTheSameChannelJoinCommand_whenUsersCommandHandle_thenThrowException() {
        final String userName = "userName";
        final String channelName = "channelName";

        try (MockedStatic<CommandUtils> commandUtilsStatic = Mockito.mockStatic(CommandUtils.class)) {

            commandUtilsStatic.when(() -> CommandUtils.getAttributeByNameWithException(ctx,
                                                                                       Constants.USER_NAME_ATTR_NAME))
                              .thenReturn(userName);
            commandUtilsStatic.when(() -> CommandUtils.getAttributeByName(ctx,
                                                                          Constants.CHANNEL_NAME_ATTR_NAME))
                              .thenReturn(Optional.of(channelName));

            JoinCommand command = new JoinCommand();
            command.init(new String[]{channelName});

            assertThrows(UserAlreadyJoinedChannelException.class, () -> {
                commandHandler.handle(ctx, command);
            });
        }
    }

}
