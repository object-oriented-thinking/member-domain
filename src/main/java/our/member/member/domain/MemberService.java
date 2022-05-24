package our.member.member.domain;

import org.springframework.stereotype.Service;
import our.member.member.error.DuplicatedEmailException;
import our.member.member.error.MemberNotFoundException;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member join(Member member) {
        if (memberRepository.isDuplicatedFromMember(member.getEmail())) {
            throw new DuplicatedEmailException();
        }

        if (memberRepository.isDuplicatedFromApplicant(member.getEmail())) {
            Member applicant = memberRepository.findByEmail(member.getEmail().getEmail()).orElseThrow(MemberNotFoundException::new);
            return memberRepository.save(applicant.reApplyMember(member));
        }

        return memberRepository.save(member);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }

}
