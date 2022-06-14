package our.member.auth.domain;

import java.util.Optional;
import java.util.UUID;

public interface AuthenticationDetailsRepository {
    Optional<AuthenticationDetails> findByMemberId(UUID memberId);

    AuthenticationDetails save(AuthenticationDetails authenticationDetails);
}
