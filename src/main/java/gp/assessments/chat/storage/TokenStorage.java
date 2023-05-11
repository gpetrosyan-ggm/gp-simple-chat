package gp.assessments.chat.storage;

/**
 * Token storage
 */
public interface TokenStorage {

    /**
     * Check if the token exists in the storage or not
     *
     * @param token which needs to check
     * @return true in token exists, otherwise false
     */
    boolean existByToken(final String token);

    /**
     * Saves the token in the storage
     *
     * @param token which needs to save
     */
    void save(final String token);

    /**
     * Remove the token from the storage
     *
     * @param token which needs to remove
     */
    void remove(String token);

}
