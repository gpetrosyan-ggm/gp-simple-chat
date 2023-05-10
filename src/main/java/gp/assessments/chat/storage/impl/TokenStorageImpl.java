package gp.assessments.chat.storage.impl;

import gp.assessments.chat.storage.TokenStorage;

import java.util.concurrent.CopyOnWriteArraySet;

public class TokenStorageImpl implements TokenStorage {

    private final CopyOnWriteArraySet<String> tokens = new CopyOnWriteArraySet<>();

    private TokenStorageImpl() {
    }

    public static TokenStorage getInstance() {
        return LoadTokenStorage.INSTANCE;
    }

    @Override
    public boolean existByToken(final String token) {
        return tokens.contains(token);
    }

    @Override
    public void save(final String token) {
        tokens.add(token);
    }

    @Override
    public void remove(String token) {
        tokens.remove(token);
    }

    private static class LoadTokenStorage {
        static final TokenStorage INSTANCE = new TokenStorageImpl();
    }

}
