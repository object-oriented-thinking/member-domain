package our.member.member.domain;

import our.member.member.error.NonMemberException;
import our.member.member.error.NotInputSpecialSymbolException;

import javax.persistence.*;
import java.util.UUID;

public class Member {
    private Long id;
    private Username username;
    private Email email;
    private Password password;
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
}
