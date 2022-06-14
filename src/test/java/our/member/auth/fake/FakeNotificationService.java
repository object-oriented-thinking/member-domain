package our.member.auth.fake;

import our.member.auth.domain.NotificationService;
import our.member.auth.domain.Token;

public class FakeNotificationService implements NotificationService {
    @Override
    public boolean sendToMail(String email, Token token) {
        return !email.equals("no-reply@mail.com");
    }
}
