package gp.assessments.chat.storage;

import gp.assessments.chat.common.entity.UserEntity;

import java.util.Optional;

/**
 * User storage
 */
public interface UserStorage {

    /**
     * Tries to find user by username
     *
     * @param userName logged in user name
     * @return the user as an optional object
     */
    Optional<UserEntity> findByUserName(String userName);

    /**
     * Saves user in the storage
     *
     * @param user which needs to storage
     * @return saved user
     */
    UserEntity save(UserEntity user);

    /**
     * Updates user data, adding last joiner channel name
     *
     * @param userName    logged in user name
     * @param channelName joined channel name
     */
    void updateLastChannelName(String userName, String channelName);

}
