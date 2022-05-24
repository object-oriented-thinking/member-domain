package our.member.member.domain;

public interface PasswordPolicy {
    boolean isCorrectPassword(String password);
}
