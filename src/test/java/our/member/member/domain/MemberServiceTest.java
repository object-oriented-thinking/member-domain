package our.member.member.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import our.member.member.error.DuplicatedEmailException;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    private final MemberService memberService = new MemberService(new FakeMemberRepository());

    @Test
    @DisplayName("회원가입을 하기 위해 이메일과 지원자 이름, 그리고 비밀번호를 가지고 회원가입을 요청한다.")
    void test1() {
        //given
        Member requestMember = new Member("user", "email@gmail.com", "password!");
        //when
        Member joinMember = memberService.join(requestMember);
        //then
        assertThat(requestMember.getUsername()).isEqualTo(joinMember.getUsername());
    }

    @Test
    @DisplayName("회원(`MEMBER`)과 이메일 중복이 되면 DuplicatedEmailException 예외가 발생한다.")
    void test2() {
        Member 기존회원 = new Member(null, "기존 회원", "email@gmail.com", "password!", MemberType.MEMBER);
        memberService.join(기존회원);

        Member requestMember = new Member("user", "email@gmail.com", "password!");

        Assertions.assertThrows(
                DuplicatedEmailException.class, () -> memberService.join(requestMember)
        );
    }

//TODO 지원자(`APPLICANT`)가 재지원하게 된다면, 이전 정보를 삭제하고 현재 지원자의 정보를 저장한다.

//TODO 지원자(`APPLICANT`)의 정보를 저장한다.

//TODO 인증을 요청한다.

//TODO 지원자 이메일 정보를 가져와 지원자의 회원 가입을 허락한다.
//TODO 지원자(`APPLICANT`)만 회원 가입을 허락할 수 있다.
//TODO 회원(`MEMBER`)으로 변경한다.

//TODO 회원의 이름 정보를 가져와 회원 정보 수정한다.
//TODO 사용자 이름 정보를 변경하고, 사용자 이름 정책에 맞아야 한다.

//TODO 회원은 현재 비밀번호의 정보와 새 비밀번호 정보를 가져와 비밀번호 변경한다.
//TODO 회원만 비밀번호를 수정할 수 있다.
//TODO 현재 비밀번호가 맞아야 한다.
//TODO 새 비밀번호는 비밀번호의 정책에 맞아야 한다.

//TODO 회원은 본인 인증이 완료되면 회원 탈퇴를 진행한다.
//TODO 회원만 회원 탈퇴를 진행할 수 있다.
//TODO 사용자 회원 탈퇴 정책인 본인 인증 완료가 되어야 한다.

}