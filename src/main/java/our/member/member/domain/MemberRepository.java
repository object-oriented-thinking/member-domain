package our.member.member.domain;

public interface MemberRepository {
    Member save(Member member);

    boolean isDuplicated(Email email);
}
