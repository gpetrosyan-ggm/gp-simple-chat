package gp.assessments.chat.storage.impl;

import gp.assessments.chat.common.entity.UserEntity;
import gp.assessments.chat.storage.UserStorage;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserStorageImpl implements UserStorage {

    private final ConcurrentHashMap<String, UserEntity> usersMap = new ConcurrentHashMap<>();

    private UserStorageImpl() {
    }

    public static UserStorage getInstance() {
        return LoadUserStorage.INSTANCE;
    }

    @Override
    public Optional<UserEntity> findByUserName(final String userName) {
        return Optional.ofNullable(usersMap.get(userName));
    }

    @Override
    public UserEntity save(final UserEntity user) {
        usersMap.put(user.getUserName(), user);
        return usersMap.get(user.getUserName());
    }

    @Override
    public void updateLastChannelName(final String userName, final String channelName) {
        Optional.ofNullable(usersMap.get(userName)).ifPresent(user -> user.setLastChannelName(channelName));
    }

    private static class LoadUserStorage {
        static final UserStorage INSTANCE = new UserStorageImpl();
    }

}
