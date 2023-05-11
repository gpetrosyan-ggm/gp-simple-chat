package gp.assessments.chat.common.entity;

import lombok.Getter;

/**
 * Token entity model
 */
@Getter
public class TokenEntity {

    private final String userId;
    private final String token;

    public TokenEntity(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

}
