package gp.assessments.chat.security;

import io.jsonwebtoken.Jwts;

import java.util.Date;

public final class TokenProvider {

    private TokenProvider() {
    }

    private static final String USER_ID_KEY = "user_id";

    public static String generateToken(final String userId) {
        return Jwts.builder()
                   .claim(USER_ID_KEY, userId)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000)))
                   .compact();
    }

}
