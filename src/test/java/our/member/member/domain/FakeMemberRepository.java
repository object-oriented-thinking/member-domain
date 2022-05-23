package our.member.member.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeMemberRepository implements MemberRepository {
    private final Map<UUID, Member> memberMap = new HashMap<>();

    public Member save(Member requestMember) {
        UUID uuid = UUID.randomUUID();

        Member responseMember = new Member(uuid, requestMember.getUsername().getUsername(), requestMember.getEmail().getEmail(), requestMember.getPassword().getPassword(), requestMember.getMemberType());

        memberMap.put(uuid, responseMember);
        return responseMember;
    }

    @Override
    public boolean isDuplicated(Email email) {
        return memberMap.values().stream()
                .filter(member -> member.getMemberType().equals(MemberType.MEMBER))
                .map(Member::getEmail)
                .anyMatch(email1 -> email1.equals(email));
    }

    public void delete(Member requestMember) {
//        UUID targetMember = memberMap.entrySet().stream()
//                            .filter(member -> member.equals(requestMember)
//
//                                    );
//        memberMap.remove(targetMember);
    }

//    public Member apply(Member applyMember) {
//
//    }
}
