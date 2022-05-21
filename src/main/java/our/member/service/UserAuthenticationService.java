package our.member.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import our.member.domain.Token;
import our.member.domain.UserEntity;
import our.member.repository.TokenRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserAuthenticationService implements authenticationService{
    private final TokenRepository tokenRepository;
    private static Map<String, String> mailSender = new HashMap<>();

    public UserAuthenticationService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void sendTokenToEmail(UserEntity userEntity) {
        // token 생성
        String token = userEntity.getEmail() + userEntity.getUsername();
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setUserId(userEntity.getUserId());
        tokenRepository.save(tokenEntity);

        mailSender.put("email", userEntity.getEmail());
        mailSender.put("title", "인증 이메일");
        mailSender.put("message", "인증 토큰: "+token);

        for (String key : mailSender.keySet()) {
            System.out.println(key + ": " + mailSender.get(key));
        }


    }

    @Override
    public boolean verifyToken(UserEntity userEntity, String token) {
        Optional<Token> tokenOptional = tokenRepository.findByToken(token);
        if (tokenOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        // 존재함
        Token existToken = tokenOptional.get();
        if (existToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("인증 유효시간이 만료된 토큰입니다.");
        }

        if (!existToken.getTokenId().equals(userEntity.getUserId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return true;
    }



}
