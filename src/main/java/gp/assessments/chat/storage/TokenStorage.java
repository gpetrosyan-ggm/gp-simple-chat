package gp.assessments.chat.storage;

public interface TokenStorage {

    boolean existByToken(final String token);

    void save(final String token);

    void remove(String token);

}
