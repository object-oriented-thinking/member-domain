package our.member.member.domain;

import our.member.member.error.NonMemberException;

import java.util.UUID;

public class Member {
    private Long id;
    private final Username username;
    private final Email email;
    private final Password password;
    private MemberType memberType;

    public Member(UUID id, String username, String email, String password, MemberType memberType) {
        if (!memberType.equals(MemberType.ADMIN) && !memberType.equals(MemberType.APPLICANT) && !memberType.equals(MemberType.MEMBER)) {
            throw new NonMemberException();
        }
        this.username = new Username(username);
        this.email = new Email(email);
        this.password = new Password(password);
        this.memberType = memberType;
    }

    public Member(String username, String email, String password) {
        this(null, username, email, password, MemberType.APPLICANT);
    }

    public Username getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public MemberType getMemberType() {
        return memberType;
    }
}
