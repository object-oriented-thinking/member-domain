package our.member.member.fixture;

import our.member.member.domain.Member;
import our.member.member.domain.MemberType;
import our.member.member.domain.Password;
import our.member.member.domain.PasswordPolicy;

public class MemberFixture {

    private static final PasswordPolicy passwordPolicy = new FakePasswordPolicy();
    private static final Password password = new Password("password!", passwordPolicy);
    public static final Member MEMBER_TYPE_MEMBER = new Member(null, "기존 회원", "email@gmail.com", password, MemberType.MEMBER);
    public static final Member APPLICANT_TYPE_MEMBER = new Member(null, "기존 회원", "email@gmail.com", password, MemberType.APPLICANT);
    public static final Member REQUEST_MEMBER = new Member("user", "email@gmail.com", password);
    public static final Member FAILED_REQUEST_MEMBER = new Member("user", "noauthentication@gmail.com", password);
}
