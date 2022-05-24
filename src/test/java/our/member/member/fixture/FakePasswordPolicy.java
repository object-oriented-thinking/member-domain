package our.member.member.fixture;

import our.member.member.domain.PasswordPolicy;

public class FakePasswordPolicy implements PasswordPolicy {
    @Override
    public boolean isCorrectPassword(String password) {
        return password.contains("!");
    }
}
