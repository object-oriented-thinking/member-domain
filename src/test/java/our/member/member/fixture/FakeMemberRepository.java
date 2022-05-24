package our.member.member.fixture;

import our.member.member.domain.Email;
import our.member.member.domain.Member;
import our.member.member.domain.MemberRepository;
import our.member.member.domain.MemberType;

import java.util.*;

public class FakeMemberRepository implements MemberRepository {
    private final Map<UUID, Member> memberMap = new HashMap<>();

    public Member save(Member requestMember) {
        UUID uuid = requestMember.getId();
        if (requestMember.getId() == null) {
            uuid = UUID.randomUUID();
        }
        Member responseMember = new Member(uuid, requestMember.getUsername(), requestMember.getEmail().getEmail(), requestMember.getPassword(), requestMember.getMemberType());
        memberMap.put(uuid, responseMember);
        return responseMember;
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberMap.values().stream().filter(member -> member.getEmail().getEmail().equals(email)).findFirst();
    }

    @Override
    public Optional<Member> findById(UUID memberId) {
        return memberMap.values().stream().filter(member -> member.getId().equals(memberId)).findFirst();

    }

    @Override
    public boolean isDuplicatedFromMember(Email email) {
        return memberMap.values().stream()
                .filter(member -> member.getMemberType().equals(MemberType.MEMBER))
                .map(Member::getEmail)
                .anyMatch(email1 -> email1.equals(email));
    }

    @Override
    public boolean isDuplicatedFromApplicant(Email email) {
        return memberMap.values().stream()
                .filter(member -> member.getMemberType().equals(MemberType.APPLICANT))
                .map(Member::getEmail)
                .anyMatch(email1 -> email1.equals(email));
    }

}
