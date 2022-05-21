package our.member.repository;

import our.member.domain.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository {

    Token save(Token token);
    Optional<Token> findById(Long id);
    Optional<Token> findByToken(String token);
    List<Token> findAll();


}
