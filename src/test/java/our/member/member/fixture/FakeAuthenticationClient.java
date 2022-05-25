package our.member.member.fixture;

import our.member.member.domain.AuthenticationClient;
import our.member.member.domain.Member;

import static our.member.member.fixture.MemberFixture.FAILED_REQUEST_MEMBER;

public class FakeAuthenticationClient implements AuthenticationClient {
    @Override
    public boolean requestAuthentication(Member member) {
        return !member.getEmail().equals(FAILED_REQUEST_MEMBER.getEmail());
    }
}
