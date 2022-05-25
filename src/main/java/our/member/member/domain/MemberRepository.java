package our.member.member.domain;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByEmail(String email);
    Optional<Member> findById(UUID memberId);
    boolean isDuplicatedFromMember(Email email);
    boolean isDuplicatedFromApplicant(Email email);
    void deleteById(UUID memberId);
}
