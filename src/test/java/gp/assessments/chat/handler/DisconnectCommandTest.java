package gp.assessments.chat.handler;

import gp.assessments.chat.command.DisconnectCommand;
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

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class DisconnectCommandTest {

    @InjectMocks
    private DisconnectCommandHandler commandHandler;

    @Mock
    private ChannelHandlerContext ctx;

    @Test
    @DisplayName("Users command handler when user logged in")
    void givenDisconnectCommandLoggedInUser_whenDisconnectCommandHandle_thenDisconnectUser() {
        try (MockedStatic<CommandUtils> commandUtilsStatic = Mockito.mockStatic(CommandUtils.class)) {
            final String userName = "userName";
            final String token = "token";

            commandUtilsStatic.when(() -> CommandUtils.getAttributeByName(ctx, Constants.USER_NAME_ATTR_NAME))
                              .thenReturn(Optional.of(userName));
            commandUtilsStatic.when(() -> CommandUtils.getAttributeByName(ctx, Constants.TOKEN_ATTR_NAME))
                              .thenReturn(Optional.of(token));

            doReturn(mock(Channel.class)).when(ctx).channel();

            commandHandler.handle(ctx, new DisconnectCommand());
        }
    }

    @Test
    @DisplayName("Users command handler when user logout")
    void givenDisconnectCommandNotLoggedInUser_whenDisconnectCommandHandle_thenDisconnectUser() {
        try (MockedStatic<CommandUtils> commandUtilsStatic = Mockito.mockStatic(CommandUtils.class)) {

            commandUtilsStatic.when(() -> CommandUtils.getAttributeByName(ctx, Constants.USER_NAME_ATTR_NAME))
                              .thenReturn(Optional.empty());
            commandUtilsStatic.when(() -> CommandUtils.getAttributeByName(ctx, Constants.CHANNEL_NAME_ATTR_NAME))
                              .thenReturn(Optional.empty());
            doReturn(mock(Channel.class)).when(ctx).channel();

            commandHandler.handle(ctx, new DisconnectCommand());
        }
    }

}
