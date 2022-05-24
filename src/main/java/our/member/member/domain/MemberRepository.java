package our.member.member.domain;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    void delete(Member member);
//    Member apply(Member member);

    Optional<Member> findByEmail(String email);

    boolean isDuplicatedFromMember(Email email);
    boolean isDuplicatedFromApplicant(Email email);
}
