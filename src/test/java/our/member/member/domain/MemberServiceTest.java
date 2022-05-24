package our.member.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import our.member.member.error.*;

import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static our.member.member.domain.MemberFixture.*;

class MemberServiceTest {

    private final MemberRepository memberRepository = new FakeMemberRepository();
    private final AuthenticationClient authentication = new FakeAuthenticationClient();
    private final PasswordPolicy passwordPolicy = new FakePasswordPolicy();
    private final MemberService memberService = new MemberService(memberRepository, authentication, passwordPolicy);

    @Test
    @DisplayName("회원가입을 하기 위해 이메일과 지원자 이름, 그리고 비밀번호를 가지고 회원가입을 요청한다.")
    void test1() {
        //given & when
        Member applyMember = memberService.apply(REQUEST_MEMBER);
        //then
        assertThat(REQUEST_MEMBER.getUsername()).isEqualTo(applyMember.getUsername());
    }

    @Test
    @DisplayName("회원(`MEMBER`)과 이메일 중복이 되면 DuplicatedEmailException 예외가 발생한다.")
    void test2() {
        //given
        memberRepository.save(MEMBER_TYPE_MEMBER);
        // when
        Member requestMember = new Member("user", "email@gmail.com", new Password("passwrod!", passwordPolicy));
        //then
        assertThrows(
                DuplicatedEmailException.class, () -> memberService.apply(requestMember)
        );
    }

    @Test
    @DisplayName("지원자(`APPLICANT`)가 재지원하게 된다면, 이전 정보를 삭제하고 지원자의 정보를 저장한다.")
    void test3() {
        //given
        Member member = memberRepository.save(APPLICANT_TYPE_MEMBER);

        String email = member.getEmail().getEmail();
        Member requestReApplyMember = new Member("다시지원한사용자", email, new Password("passwrod!", passwordPolicy));

        //when
        Member responseReApplyMember = memberService.apply(requestReApplyMember);

        //then
        assertThat(Objects.requireNonNull(memberRepository.findByEmail(email).orElse(null)).getUsername()).isNotEqualTo(member.getUsername());
        assertThat(Objects.requireNonNull(memberRepository.findByEmail(email).orElse(null)).getUsername()).isEqualTo(responseReApplyMember.getUsername());
    }

    @Test
    @DisplayName("지원자(`APPLICANT`)가 아니면 예외가 발생한다.")
    void test4() {
        //given
        Member requestMember = new Member("지원자", "email@gmail.com", new Password("passwrod!", passwordPolicy));
        //when
        requestMember.makeMember();
        //then
        assertThatThrownBy(() -> memberService.apply(requestMember)).isInstanceOf(NonApplicantException.class);
    }


    @Test
    @DisplayName("인증이 실패하면 AuthenticationFailedException 예외가 발생한다.")
    void test5() {
        assertThatThrownBy(
                () -> memberService.apply(FAILED_REQUEST_MEMBER)
        ).isInstanceOf(AuthenticationFailedException.class);
    }

    @Test
    @DisplayName("지원자 이메일 정보를 가져와 지원자의 회원 가입을 허락한다.")
    void test6() {
        //given
        Member member = memberRepository.save(APPLICANT_TYPE_MEMBER);
        String email = member.getEmail().getEmail();
        //when & then
        assertDoesNotThrow(() -> memberService.acceptJoin(email));
    }

    @Test
    @DisplayName("지원자(`APPLICANT`)만 회원 가입을 허락할 수 있다.")
    void test7() {
        //given
        Member member = memberRepository.save(MEMBER_TYPE_MEMBER);
        String email = member.getEmail().getEmail();
        //when & then
        assertThatThrownBy(() -> memberService.acceptJoin(email)).isInstanceOf(NotAllowedEmailException.class);
    }

    @Test
    @DisplayName("회원(`MEMBER`)으로 변경한다.")
    void test8() {
        //given
        Member member = memberRepository.save(APPLICANT_TYPE_MEMBER);
        String email = member.getEmail().getEmail();
        //when
        memberService.acceptJoin(email);
        //then
        assertThat(member.getMemberType()).isEqualTo(MemberType.MEMBER);
    }

    @Test
    @DisplayName("회원의 이름 정보를 가져와 사용자 이름을 수정한다.")
    void test9() {
        //given
        Member member = memberRepository.save(MEMBER_TYPE_MEMBER);
        UUID id = member.getId();
        String username = "변경하려는 이름";
        //when
        Member modifyMember = memberService.modifyUsername(id, username);
        //then
        assertThat(modifyMember.getUsername()).isEqualTo(new Username(username));
    }

    @Test
    @DisplayName("사용자의 유형이 회원이아니면 NonMemberException 예외가 발생한다.")
    void test10() {
        //given
        Member member = memberRepository.save(APPLICANT_TYPE_MEMBER);
        UUID id = member.getId();
        String username = "변경하려는 이름";
        //when & then
        assertThatThrownBy(() -> memberService.modifyUsername(id, username)).isInstanceOf(NonMemberException.class);
    }

    @Test
    @DisplayName("기존 이름으로 변경하면 DuplicatedUsernameException 예외가 발생한다.")
    void test11() {
        //given
        Member member = memberRepository.save(MEMBER_TYPE_MEMBER);
        UUID id = member.getId();
        String username = member.getUsername().getUsername();
        //when & then
        assertThatThrownBy(() -> memberService.modifyUsername(id, username)).isInstanceOf(DuplicatedUsernameException.class);
    }

    @Test
    @DisplayName("회원은 현재 비밀번호의 정보와 새 비밀번호 정보를 가져와 비밀번호 변경한다.")
    public void test12() {
        //given
        Member member = memberRepository.save(MEMBER_TYPE_MEMBER);
        UUID id = member.getId();
        String password = "password!!";
        //when
        Member modifyMember = memberService.modifyPassword(id, password);
        //then
        assertThat(modifyMember.getPassword()).isEqualTo(new Password(password, passwordPolicy));
    }

    @Test
    @DisplayName("회원만 비밀번호를 수정할 수 있다.")
    public void test13() throws Exception {
        //given
        Member member = memberRepository.save(APPLICANT_TYPE_MEMBER);
        UUID id = member.getId();
        String password = "password!!";
        //when & then
        assertThatThrownBy(
                () -> memberService.modifyPassword(id, password)
        ).isInstanceOf(NonMemberException.class);
    }

    @Test
    @DisplayName("현재 비밀번호와 같으면 예외가 발생한다.")
    public void test14() throws Exception {
        //given
        Member member = memberRepository.save(MEMBER_TYPE_MEMBER);
        UUID id = member.getId();
        String password = "password!";
        //when & then
        assertThatThrownBy(
                () -> memberService.modifyPassword(id, password)
        ).isInstanceOf(DuplicatedPasswordException.class);
    }

//TODO 회원은 본인 인증이 완료되면 회원 탈퇴를 진행한다.
//TODO 회원만 회원 탈퇴를 진행할 수 있다.
//TODO 사용자 회원 탈퇴 정책인 본인 인증 완료가 되어야 한다.

}