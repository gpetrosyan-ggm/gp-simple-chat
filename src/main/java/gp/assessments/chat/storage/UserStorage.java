package gp.assessments.chat.storage;

import gp.assessments.chat.common.entity.UserEntity;

import java.util.Optional;

public interface UserStorage {

    Optional<UserEntity> findByUserName(String userName);

    UserEntity save(UserEntity user);

    void remove(final String userName);

}
