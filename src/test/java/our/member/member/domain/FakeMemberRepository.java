package our.member.member.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeMemberRepository implements MemberRepository{
    private final Map<UUID, Member> memberMap = new HashMap<>();

    public Member save(Member requestMember) {
        UUID uuid = UUID.randomUUID();

        Member responseMember = new Member(uuid, requestMember.getUsername().getUsername(), requestMember.getEmail().getEmail(), requestMember.getPassword().getPassword(), MemberType.APPLICANT);

        memberMap.put(uuid, responseMember);
        return responseMember;
    }
}
