package our.member.authentication.repository;

import our.member.authentication.domain.AuthenticationDetails;

import java.util.List;
import java.util.Optional;

public interface TokenRepository {

    AuthenticationDetails save(AuthenticationDetails authenticationDetails);
    Optional<AuthenticationDetails> findById(Long id);
//    Optional<AuthenticationDetails> findByToken(String token);
    List<AuthenticationDetails> findAll();


}
