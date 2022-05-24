package our.member.member.domain;

import org.springframework.stereotype.Service;
import our.member.member.error.*;

import java.util.UUID;

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

        if (!member.isApplicant()) {
            throw new NonApplicantException();
        }

        if (!authentication.requestAuthentication(member)) {
            throw new AuthenticationFailedException();
        }
        return memberRepository.save(member);
    }

    public void acceptJoin(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        if (member.getMemberType().equals(MemberType.MEMBER)) {
            throw new NotAllowedEmailException();
        }
        member.makeMember();
    }

    public Member modifyUsername(UUID memberId, Username username) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        if (!member.isMember()) {
            throw new NonMemberException();
        }

        if (member.getUsername().equals(username)) {
            throw new DuplicatedUsernameException();
        }

        member.rename(username);
        return member;
    }

    public Member modifyPassword(UUID memberId, Password password) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        if (!member.isMember()) {
            throw new NonMemberException();
        }

        if (member.getPassword().equals(password)) {
            throw new DuplicatedPasswordException();
        }

        member.modifyPassword(password);
        return member;
    }
}
