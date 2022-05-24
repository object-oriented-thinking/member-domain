package our.member.member.domain;

import static our.member.member.domain.MemberFixture.인증에실패하는회원;

public class FakeAuthenticationClient implements AuthenticationClient {
    @Override
    public boolean requestAuthentication(Member member) {
        return !member.getEmail().equals(인증에실패하는회원.getEmail());
    }
}
