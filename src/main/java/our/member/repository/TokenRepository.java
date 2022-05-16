package our.member.repository;

import our.member.domain.TokenEntity;

import java.util.List;
import java.util.Optional;

public interface TokenRepository {

    TokenEntity save(TokenEntity tokenEntity);
    Optional<TokenEntity> findById(Long id);
    Optional<TokenEntity> findByToken(String token);
    List<TokenEntity> findAll();


}
