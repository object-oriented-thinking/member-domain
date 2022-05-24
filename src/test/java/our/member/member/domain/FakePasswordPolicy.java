package our.member.member.domain;

public class FakePasswordPolicy implements PasswordPolicy{
    @Override
    public boolean isCorrectPassword(String password) {
        return password.contains("!");
    }
}
