package our.member.member.fake;

import org.springframework.stereotype.Component;
import our.member.member.domain.AuthenticationClient;
import our.member.member.domain.Email;
import our.member.member.domain.Member;

@Component
public class FakeAuthenticationClient implements AuthenticationClient {
    @Override
    public boolean requestAuthentication(Member member) {
        return !member.getEmail().equals(new Email("noauthentication@gmail.com"));
    }
}
