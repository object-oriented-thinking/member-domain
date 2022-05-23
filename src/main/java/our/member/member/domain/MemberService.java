package our.member.member.domain;

import org.springframework.stereotype.Service;
import our.member.member.error.DuplicatedEmailException;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //TODO 서비스 기능 구현
    public Member join(Member member) {
        if (memberRepository.isDuplicated(member.getEmail())) {
            throw new DuplicatedEmailException();
        }
        return memberRepository.save(member);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }

//    public Member apply(Member member) {
//            if (memberRepository.isDuplicated(member.getEmail())) {
//                throw new DuplicatedEmailException();
//            }
//            return memberRepository.save(member);
//    }

}
