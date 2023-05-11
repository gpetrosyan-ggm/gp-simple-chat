package gp.assessments.chat.security;

import gp.assessments.chat.common.entity.UserEntity;
import gp.assessments.chat.common.error.UserIncorrectPasswordException;
import gp.assessments.chat.storage.impl.UserStorageImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationManagerTest {

    @InjectMocks
    private AuthenticationManager authenticationManager;

    @Test
    @DisplayName("Authenticate with existing user's correct credentials")
    void givenUserCorrectCredentials_whenAuthenticate_thenDoLogin() {
        try (MockedStatic<UserStorageImpl> userStorageStatic = Mockito.mockStatic(UserStorageImpl.class)) {
            UserStorageImpl userStorageStaticMock = mock(UserStorageImpl.class);
            userStorageStatic.when(UserStorageImpl::getInstance).thenReturn(userStorageStaticMock);

            final String userName = "username";
            final String password = "password";
            final UserEntity user = new UserEntity(userName, password);

            when(userStorageStaticMock.findByUserName(anyString())).thenReturn(Optional.of(user));

            String token = authenticationManager.authenticate(userName, password);
            Assertions.assertTrue(token != null && !token.isBlank());
        }
    }

    @Test
    @DisplayName("Authenticate with existing user's wrong credentials")
    void givenUserWrongCredentials_whenAuthenticate_thenThrowException() {
        try (MockedStatic<UserStorageImpl> userStorageStatic = Mockito.mockStatic(UserStorageImpl.class)) {
            UserStorageImpl userStorageStaticMock = mock(UserStorageImpl.class);
            userStorageStatic.when(UserStorageImpl::getInstance).thenReturn(userStorageStaticMock);

            final String userName = "username";
            final String password = "password";
            final String wornPassword = "wrongPassword";
            final UserEntity user = new UserEntity(userName, password);

            when(userStorageStaticMock.findByUserName(anyString())).thenReturn(Optional.of(user));

            assertThrows(UserIncorrectPasswordException.class, () -> {
                authenticationManager.authenticate(userName, wornPassword);
            });
        }
    }

    @Test
    @DisplayName("Authenticate with not existing user's correct credentials")
    void givenNotExistsUserCredentials_whenAuthenticate_thenDoLogin() {
        try (MockedStatic<UserStorageImpl> userStorageStatic = Mockito.mockStatic(UserStorageImpl.class)) {
            UserStorageImpl userStorageStaticMock = mock(UserStorageImpl.class);
            userStorageStatic.when(UserStorageImpl::getInstance).thenReturn(userStorageStaticMock);

            final String userName = "username";
            final String password = "password";
            final UserEntity user = new UserEntity(userName, password);

            when(userStorageStaticMock.findByUserName(anyString())).thenReturn(Optional.empty());
            when(userStorageStaticMock.save(any())).thenReturn(user);

            String token = authenticationManager.authenticate(userName, password);
            Assertions.assertTrue(token != null && !token.isBlank());
        }
    }

    @Test
    @DisplayName("Logout user with existing username")
    void givenExistingUserCredentials_whenLogout_thenDoNothing() {
        try (MockedStatic<UserStorageImpl> userStorageStatic = Mockito.mockStatic(UserStorageImpl.class)) {
            UserStorageImpl userStorageStaticMock = mock(UserStorageImpl.class);
            userStorageStatic.when(UserStorageImpl::getInstance).thenReturn(userStorageStaticMock);

            final String userName = "username";
            final String password = "password";
            final UserEntity user = new UserEntity(userName, password);

            when(userStorageStaticMock.findByUserName(anyString())).thenReturn(Optional.of(user));

            authenticationManager.logout(userName, "token");
        }
    }

    @Test
    @DisplayName("Logout user with not existing username")
    void givenNotExistingUserCredentials_whenLogout_thenDoNothing() {
        try (MockedStatic<UserStorageImpl> userStorageStatic = Mockito.mockStatic(UserStorageImpl.class)) {
            UserStorageImpl userStorageStaticMock = mock(UserStorageImpl.class);
            userStorageStatic.when(UserStorageImpl::getInstance).thenReturn(userStorageStaticMock);

            when(userStorageStaticMock.findByUserName(anyString())).thenReturn(Optional.empty());

            authenticationManager.logout("username", "token");
        }
    }

}
