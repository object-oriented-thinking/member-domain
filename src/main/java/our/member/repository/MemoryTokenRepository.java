package our.member.repository;

import our.member.domain.TokenEntity;

import java.time.LocalDateTime;
import java.util.*;

public class MemoryTokenRepository implements TokenRepository {
    private final Map<Long, TokenEntity> store = new HashMap<>();
    private static Long sequence = 0L;


    @Override
    public TokenEntity save(TokenEntity tokenEntity) {
        tokenEntity.setTokenId(sequence++);
        tokenEntity.setCreatedAt(LocalDateTime.now());
        tokenEntity.setExpiresAt(LocalDateTime.now().plusMinutes(30));
        store.put(tokenEntity.getTokenId(), tokenEntity);
        return tokenEntity;
    }

    @Override
    public Optional<TokenEntity> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<TokenEntity> findByToken(String token) {
        return store.values().stream()
                .filter(tokenEntity -> tokenEntity.getToken().equals(token))
                .findAny();
    }

    @Override
    public List<TokenEntity> findAll() {
        return new ArrayList<>(store.values());
    }
}
