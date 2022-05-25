package our.member.member.domain;

import our.member.member.error.NonMemberException;

import java.util.UUID;

public class Member {
    private final UUID id;
    private Username username;
    private final Email email;
    private Password password;
    private MemberType memberType;

    public Member(UUID id, Username username, String email, Password password, MemberType memberType) {
        if (!memberType.equals(MemberType.ADMIN) && !memberType.equals(MemberType.APPLICANT) && !memberType.equals(MemberType.MEMBER)) {
            throw new NonMemberException();
        }
        this.id = id;
        this.username = username;
        this.email = new Email(email);
        this.password = password;
        this.memberType = memberType;
    }

    public Member(Username username, String email, Password password) {
        this(null, username, email, password, MemberType.APPLICANT);
    }

    public UUID getId() {
        return id;
    }

    public Member reApplyMember(Member member) {
        return new Member(this.id, member.getUsername(), member.getEmail().getEmail(), member.getPassword(), MemberType.APPLICANT);
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

    public void modifyPassword(Password password) {
        this.password = password;
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
