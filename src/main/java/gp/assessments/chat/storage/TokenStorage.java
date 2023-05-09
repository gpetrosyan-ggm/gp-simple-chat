package gp.assessments.chat.storage;

public interface TokenStorage {

    boolean existByToken(final String token);

    boolean save(final String token);

}
