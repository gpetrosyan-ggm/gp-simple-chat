package gp.assessments.chat.security;

import gp.assessments.chat.common.entity.UserEntity;
import gp.assessments.chat.storage.impl.UserStorageImpl;

import java.util.Optional;

public class AuthenticationManager {

    public String authenticate(String userName, String password) {
        if (userName.isBlank()) {
            throw new IllegalArgumentException("invalid user");
        }
        if (password.isBlank()) {
            throw new IllegalArgumentException("invalid password");
        }

        Optional<UserEntity> userOpt = UserStorageImpl.getInstance().findByUserName(userName);

        UserEntity user;
        if (userOpt.isEmpty()) {
            user = UserStorageImpl.getInstance().save(new UserEntity(userName, password));
        } else {
            user = userOpt.get();

            if (!user.getPassword().equals(password)) {
                throw new IllegalArgumentException("incorrect password");
            }
        }
        user.makeOnline();

        return TokenProvider.generateToken(user.getId());
    }

}
