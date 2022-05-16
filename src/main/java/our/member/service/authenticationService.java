package our.member.service;

import our.member.domain.UserEntity;

public interface authenticationService {
    // 1. token 전송
    void sendTokenToEmail(UserEntity userEntity);
    // 2. token 체크
    boolean verifyToken(UserEntity userEntity, String token);
}
