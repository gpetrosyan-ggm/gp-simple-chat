package gp.assessments.chat.auth.impl;

import gp.assessments.chat.auth.AuthService;
import gp.assessments.chat.common.entity.UserEntity;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class AuthServiceImpl implements AuthService {

    private final ConcurrentHashMap<String, UserEntity> usersMap = new ConcurrentHashMap<>();

    @Override
    public void authenticate(String userName, String password) {
        if (userName.isBlank()) {
            throw new IllegalArgumentException("invalid user");
        }
        if (password.isBlank()) {
            throw new IllegalArgumentException("invalid password");
        }

        UserEntity user = usersMap.get(userName);

        if (Objects.isNull(user)) {
            usersMap.put(userName, new UserEntity(userName, password));
            return;
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("incorrect password");
        }

        user.makeOnline();
    }

}
