package our.member.member.fake;

import org.springframework.stereotype.Component;
import our.member.member.domain.PasswordPolicy;

@Component
public class FakePasswordPolicy implements PasswordPolicy {
    @Override
    public boolean isCorrectPassword(String password) {
        return password.contains("!");
    }
}
