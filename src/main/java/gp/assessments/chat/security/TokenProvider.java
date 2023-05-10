package gp.assessments.chat.security;

import gp.assessments.chat.utils.Constants;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public final class TokenProvider {

    private TokenProvider() {
    }

    public static String generateToken(final String userId) {
        return Jwts.builder()
                   .claim(Constants.USER_ID_KEY, userId)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000)))
                   .compact();
    }

}
