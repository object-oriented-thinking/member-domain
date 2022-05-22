package our.member.member.domain;

import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //TODO 서비스 기능 구현
    public Member join(Member member) {
        return memberRepository.save(member);
    }
}
