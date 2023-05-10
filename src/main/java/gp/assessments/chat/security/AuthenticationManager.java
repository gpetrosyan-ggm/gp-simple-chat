package gp.assessments.chat.security;

import gp.assessments.chat.common.entity.UserEntity;
import gp.assessments.chat.common.error.UserIncorrectPasswordException;
import gp.assessments.chat.storage.impl.TokenStorageImpl;
import gp.assessments.chat.storage.impl.UserStorageImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class AuthenticationManager {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationManager.class);

    public String authenticate(String userName, String password) {
        logger.info("The user '{}' is logging in.", userName);
        Optional<UserEntity> userOpt = UserStorageImpl.getInstance().findByUserName(userName);

        UserEntity user;
        if (userOpt.isEmpty()) {
            logger.info("Will be created new user: '{}'.", userName);
            user = UserStorageImpl.getInstance().save(new UserEntity(userName, password));
        } else {
            user = userOpt.get();

            if (!user.getPassword().equals(password)) {
                throw new UserIncorrectPasswordException("User inputted incorrect password");
            }
        }
        user.makeOnline();

        final String token = TokenProvider.generateToken(user.getId());
        TokenStorageImpl.getInstance().save(token);

        return token;
    }

    public void logout(final String userName, final String token) {
        logger.info("The user '{}' is logged out.", userName);
        Optional<UserEntity> userOpt = UserStorageImpl.getInstance().findByUserName(userName);
        userOpt.ifPresent(UserEntity::makeOffline);
        TokenStorageImpl.getInstance().remove(token);
    }

}
