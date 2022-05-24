package our.member.member.domain;

import org.springframework.stereotype.Service;
import our.member.member.error.*;

import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationClient authentication;
    private final PasswordPolicy passwordPolicy;

    public MemberService(MemberRepository memberRepository, AuthenticationClient authentication, PasswordPolicy passwordPolicy) {
        this.memberRepository = memberRepository;
        this.authentication = authentication;
        this.passwordPolicy = passwordPolicy;
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

    public Member modifyUsername(UUID memberId, String username) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        Username memberUsername = new Username(username);

        if (!member.isMember()) {
            throw new NonMemberException();
        }

        if (member.getUsername().equals(memberUsername)) {
            throw new DuplicatedUsernameException();
        }

        member.rename(memberUsername);
        return member;
    }

    public Member modifyPassword(UUID memberId, String password) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        Password memberPassword = new Password(password, passwordPolicy);

        if (!member.isMember()) {
            throw new NonMemberException();
        }

        if (member.getPassword().equals(memberPassword)) {
            throw new DuplicatedPasswordException();
        }

        member.modifyPassword(memberPassword);
        return member;
    }
}
