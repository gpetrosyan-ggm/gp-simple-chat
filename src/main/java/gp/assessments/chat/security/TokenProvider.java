package gp.assessments.chat.security;

import gp.assessments.chat.utils.Constants;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * Token provider class
 */
public final class TokenProvider {

    private TokenProvider() {
    }

    /**
     * Generates token and adds user id in the token as a claim
     *
     * @param userId logged in user id
     * @return generated token
     */
    public static String generateToken(final String userId) {
        return Jwts.builder()
                   .claim(Constants.USER_ID_KEY, userId)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000)))
                   .compact();
    }

}
