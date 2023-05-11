package gp.assessments.chat.handler;

import gp.assessments.chat.command.LoginCommand;
import gp.assessments.chat.common.entity.UserEntity;
import gp.assessments.chat.security.AuthenticationManager;
import gp.assessments.chat.storage.impl.UserStorageImpl;
import gp.assessments.chat.utils.CommandUtils;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class LoginCommandTest {

    @InjectMocks
    private LoginCommandHandler commandHandler;

    @Mock
    private ChannelHandlerContext ctx;

    @Test
    @DisplayName("Login command handler, when the user first time login")
    void givenFirstTimeLoginCommand_whenLoginCommandHandle_thenDoLogin() {
        final String userName = "userName";
        final String password = "password";
        final String token = "token";
        final UserEntity user = new UserEntity(userName, password);

        try (MockedStatic<CommandUtils> commandUtilsStatic = Mockito.mockStatic(CommandUtils.class);
             MockedStatic<UserStorageImpl> userStorageImplStatic = Mockito.mockStatic(UserStorageImpl.class);
             MockedConstruction<AuthenticationManager> authenticationManagerConstruction = Mockito.mockConstruction(
                     AuthenticationManager.class)) {
            AuthenticationManager authenticationManager = new AuthenticationManager();
            AuthenticationManager authenticationManagerMock = authenticationManagerConstruction.constructed().get(0);

            doReturn(token).when(authenticationManagerMock).authenticate(userName, password);

            UserStorageImpl userStorageImplMock = mock(UserStorageImpl.class);
            userStorageImplStatic.when(UserStorageImpl::getInstance).thenReturn(userStorageImplMock);
            doReturn(Optional.of(user)).when(userStorageImplMock).findByUserName(userName);

            LoginCommand command = new LoginCommand();
            command.init(new String[]{userName, password});
            commandHandler.handle(ctx, command);
        }
    }

    @Test
    @DisplayName("Login command handler, when the user already has joined channel")
    void givenAlreadyJoinedChannelLoginCommand_whenLoginCommandHandle_thenDoLogin() {
        final String userName = "userName";
        final String password = "password";
        final String channelName = "channelName";
        final String token = "token";
        final UserEntity user = new UserEntity(userName, password);
        user.setLastChannelName(channelName);

        try (MockedStatic<CommandUtils> commandUtilsStatic = Mockito.mockStatic(CommandUtils.class);
             MockedStatic<UserStorageImpl> userStorageImplStatic = Mockito.mockStatic(UserStorageImpl.class);
             MockedConstruction<AuthenticationManager> authenticationManagerConstruction = Mockito.mockConstruction(
                     AuthenticationManager.class);
             MockedConstruction<JoinCommandHandler> joinCommandHandlerConstruction = Mockito.mockConstruction(
                     JoinCommandHandler.class)) {
            AuthenticationManager authenticationManager = new AuthenticationManager();
            AuthenticationManager authenticationManagerMock = authenticationManagerConstruction.constructed().get(0);

            JoinCommandHandler joinCommandHandler = new JoinCommandHandler();
            JoinCommandHandler joinCommandHandlerMock = joinCommandHandlerConstruction.constructed().get(0);

            doReturn(token).when(authenticationManagerMock).authenticate(userName, password);

            UserStorageImpl userStorageImplMock = mock(UserStorageImpl.class);
            userStorageImplStatic.when(UserStorageImpl::getInstance).thenReturn(userStorageImplMock);
            doReturn(Optional.of(user)).when(userStorageImplMock).findByUserName(userName);

            LoginCommand command = new LoginCommand();
            command.init(new String[]{userName, password});
            commandHandler.handle(ctx, command);
        }
    }

}
