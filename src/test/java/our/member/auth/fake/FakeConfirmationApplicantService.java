package our.member.auth.fake;

import our.member.auth.domain.ConfirmationApplicantService;

import java.util.UUID;

import static our.member.auth.fixture.AuthenticationFixture.*;

public class FakeConfirmationApplicantService implements ConfirmationApplicantService {

    @Override
    public boolean isApplicant(UUID memberId) {
        return memberId.equals(APPLICANT_MEMBER_ID);
    }

    @Override
    public String findEmailByMemberId(UUID memberId) {
        if (memberId.equals(NOT_INVALID_EMAIL_MEMBER_ID)) {
            return null;
        }
        return memberId.toString();
    }
}
