package our.member.member.fixture;

import our.member.member.domain.*;

public class MemberFixture {

    private static final PasswordPolicy passwordPolicy = new FakePasswordPolicy();
    private static final ProfanityPolicy profanityPolicy = new FakeProfanityPolicy();
    private static final Password password = new Password("password!", passwordPolicy);
    public static final Member MEMBER_TYPE_MEMBER = new Member(null, new Username("기존 회원", profanityPolicy), "email@gmail.com", password, MemberType.MEMBER);
    public static final Member APPLICANT_TYPE_MEMBER = new Member(null, new Username("기존 회원", profanityPolicy), "email@gmail.com", password, MemberType.APPLICANT);
    public static final Member REQUEST_MEMBER = new Member(new Username("user", profanityPolicy), "email@gmail.com", password);
    public static final Member FAILED_REQUEST_MEMBER = new Member(new Username("user", profanityPolicy), "noauthentication@gmail.com", password);
}
