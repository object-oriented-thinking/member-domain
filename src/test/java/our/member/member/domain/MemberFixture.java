package our.member.member.domain;

public class MemberFixture {
    public static final Member 기존회원 = new Member(null, "기존 회원", "email@gmail.com", "password!", MemberType.MEMBER);
    public static final Member 지원회원 = new Member(null, "기존 회원", "email@gmail.com", "password!", MemberType.APPLICANT);
    public static final Member REQUEST_MEMBER = new Member("user", "email@gmail.com", "password!");

    public static final Member 인증에실패하는회원 = new Member("user", "noauthentication@gmail.com", "passwrod!");
}
