package gp.assessments.chat.security;

import io.jsonwebtoken.Jwts;

public final class TokenProvider {

    private TokenProvider() {
    }

    private static final String USER_ID_KEY = "user_id";

    public static String generateToken(final String userId) {
        return Jwts.builder()
                   .claim(USER_ID_KEY, userId)
                   .compact();
    }

}
