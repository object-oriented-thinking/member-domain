package our.member.auth.domain;

public interface NotificationService {
    boolean sendToMail(String email, Token token);
}
