package our.member.repository;

import our.member.domain.Token;

import java.time.LocalDateTime;
import java.util.*;

public class MemoryTokenRepository implements TokenRepository {
    private final Map<Long, Token> store = new HashMap<>();
    private static Long sequence = 0L;


    @Override
    public Token save(Token token) {
        token.setTokenId(sequence++);
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiresAt(LocalDateTime.now().plusMinutes(30));
        store.put(token.getTokenId(), token);
        return token;
    }

    @Override
    public Optional<Token> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Token> findByToken(String token) {
        return store.values().stream()
                .filter(tokenEntity -> tokenEntity.getToken().equals(token))
                .findAny();
    }

    @Override
    public List<Token> findAll() {
        return new ArrayList<>(store.values());
    }
}
