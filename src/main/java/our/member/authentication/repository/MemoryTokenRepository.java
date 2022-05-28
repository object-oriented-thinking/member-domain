package our.member.authentication.repository;

import our.member.authentication.domain.AuthenticationDetails;

import java.util.*;

public class MemoryTokenRepository implements TokenRepository {
    private final Map<Long, AuthenticationDetails> store = new HashMap<>();
    private static Long sequence = 0L;


    @Override
    public AuthenticationDetails save(AuthenticationDetails authenticationDetails) {
//        authenticationDetails.setTokenId(sequence++);
//        authenticationDetails.setCreatedAt(LocalDateTime.now());
//        authenticationDetails.setExpiresAt(LocalDateTime.now().plusMinutes(30));
//        store.put(authenticationDetails.getTokenId(), authenticationDetails);
        return authenticationDetails;
    }

    @Override
    public Optional<AuthenticationDetails> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

//    @Override
//    public Optional<AuthenticationDetails> findByToken(String token) {
//        return store.values().stream()
//                .filter(authenticationDetailsEntity -> authenticationDetailsEntity.getToken().equals(token))
//                .findAny();
//    }

    @Override
    public List<AuthenticationDetails> findAll() {
        return new ArrayList<>(store.values());
    }
}
