package our.member.auth.domain;

import our.member.auth.domain.Token;

public interface NotificationService {
    boolean sendToMail(String email, Token token);
}
