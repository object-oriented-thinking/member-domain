package our.member.member.domain;

import our.member.member.error.NonMemberException;

import java.util.UUID;

public class Member {
    private final UUID id;
    private Username username;
    private final Email email;
    private final Password password;
    private MemberType memberType;

    public Member(UUID id, String username, String email, String password, MemberType memberType) {
        if (!memberType.equals(MemberType.ADMIN) && !memberType.equals(MemberType.APPLICANT) && !memberType.equals(MemberType.MEMBER)) {
            throw new NonMemberException();
        }
        this.id = id;
        this.username = new Username(username);
        this.email = new Email(email);
        this.password = new Password(password);
        this.memberType = memberType;
    }

    public Member(String username, String email, String password) {
        this(null, username, email, password, MemberType.APPLICANT);
    }

    public UUID getId() {
        return id;
    }

    public Member reApplyMember(Member member) {
        return new Member(this.id, member.getUsername().getUsername(), member.getEmail().getEmail(), member.getPassword().getPassword(), MemberType.APPLICANT);
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

    public void rename(Username username) {
        this.username = username;
    }

    public void makeMember() {
        this.memberType = MemberType.MEMBER;
    }

    public boolean isApplicant() {
        return this.memberType.equals(MemberType.APPLICANT);
    }

    public boolean isMember() {
        return this.memberType.equals(MemberType.MEMBER);
    }
}
