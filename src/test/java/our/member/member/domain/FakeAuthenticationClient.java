package our.member.member.domain;

import static our.member.member.domain.MemberFixture.FAILED_REQUEST_MEMBER;

public class FakeAuthenticationClient implements AuthenticationClient {
    @Override
    public boolean requestAuthentication(Member member) {
        return !member.getEmail().equals(FAILED_REQUEST_MEMBER.getEmail());
    }
}
