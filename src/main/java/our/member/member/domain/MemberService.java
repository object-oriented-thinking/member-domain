package our.member.member.domain;

import org.springframework.stereotype.Service;
import our.member.member.error.AuthenticationFailedException;
import our.member.member.error.DuplicatedEmailException;
import our.member.member.error.MemberNotFoundException;
import our.member.member.error.NotAllowedEmailException;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationClient authentication;

    public MemberService(MemberRepository memberRepository, AuthenticationClient authentication) {
        this.memberRepository = memberRepository;
        this.authentication = authentication;
    }

    public Member apply(Member member) {
        if (memberRepository.isDuplicatedFromMember(member.getEmail())) {
            throw new DuplicatedEmailException();
        }

        if (memberRepository.isDuplicatedFromApplicant(member.getEmail())) {
            Member applicant = memberRepository.findByEmail(member.getEmail().getEmail()).orElseThrow(MemberNotFoundException::new);
            return memberRepository.save(applicant.reApplyMember(member));
        }


        if (!authentication.requestAuthentication(member)) {
            throw new AuthenticationFailedException();
        }
        return memberRepository.save(member);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }

    public void acceptJoin(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        if (member.getMemberType().equals(MemberType.MEMBER)) {
            throw new NotAllowedEmailException();
        }
        member.changeMemberType();
    }
}
