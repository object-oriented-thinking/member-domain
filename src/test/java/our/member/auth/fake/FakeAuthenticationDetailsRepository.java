package our.member.auth.fake;

import our.member.auth.domain.AuthenticationDetails;
import our.member.auth.domain.AuthenticationDetailsRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class FakeAuthenticationDetailsRepository implements AuthenticationDetailsRepository {

    private final Map<UUID, AuthenticationDetails> authenticationDetailsMap = new HashMap<>();

    @Override
    public Optional<AuthenticationDetails> findByMemberId(UUID memberId) {
        return authenticationDetailsMap.values().stream()
            .filter(
                authenticationDetails -> authenticationDetails.getMemberId().equals(memberId)
            ).findFirst();
    }

    @Override
    public AuthenticationDetails save(AuthenticationDetails authenticationDetails) {
        return authenticationDetailsMap.put(authenticationDetails.getAuthenticationId(), authenticationDetails);
    }
}
