package our.member.member.domain;

public interface MemberRepository {
    Member save(Member member);
    void delete(Member member);
//    Member apply(Member member);
    boolean isDuplicated(Email email);

}
