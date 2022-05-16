package our.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import our.member.member.domain.Member;
import our.member.member.domain.MemberType;
import our.member.member.error.NotAllowedDomainException;
import our.member.member.error.NotEmailFormatException;
import our.member.member.error.NotInputSpecialSymbolException;
import our.member.member.error.ProfanityException;

import java.util.UUID;

class MemberTest {

    @Test
    @DisplayName("사용자 식별자, 사용자 이름, 이메일, 비밀번호, 그리고 회원 유형이 들어간다")
    void test1() {
        Assertions.assertDoesNotThrow(() -> new Member(UUID.randomUUID(), "name", "email@email.com", "password!", MemberType.ADMIN));
    }

    @Test
    @DisplayName("사용자 이름은 공백이 들어가면 IllegalArgumentException 이 발생한다. ")
    void test2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Member(UUID.randomUUID(), "", "email@email.com", "password!", MemberType.ADMIN);
        });
    }

    @Test
    @DisplayName("사용자 이름은 욕설이 들어가면 ProfanityException 이 발생한다. ")
    void test4() {
        Assertions.assertThrows(ProfanityException.class, () -> {
            new Member(UUID.randomUUID(), "fuck", "email@email.com", "password!", MemberType.ADMIN);
        });
    }

    @Test
    @DisplayName("사용자 이름은 1글자 이상, 20글자 이하의 문자가 아닌 문자가 들어가면 RuntimeException 예외가 발생한다.")
    void test5() {
        //given
        String username = "asnfjakshfjkshfjkashfkjashfkjsajkhaskjfhksjfkjshskjdfhksjdfhsdfkjs";
        //when & then
        Assertions.assertThrows(RuntimeException.class, () -> {
            new Member(UUID.randomUUID(), username, "email@email.com", "password!", MemberType.ADMIN);
        });
    }

    @Test
    @DisplayName("비밀번호에 특수문자가 들어가지 않으면 NotInputSpecialSymbolException 에외가 발생한다.")
    void test6() {
        //given
        String password = "password";
        //when & then
        Assertions.assertThrows(NotInputSpecialSymbolException.class, () -> {
            new Member(UUID.randomUUID(), "username", "email@email.com", password, MemberType.ADMIN);
        });
    }

    @Test
    @DisplayName("비밀번호는 8글자 이상 30글자 이하가 아니면 RuntimeException 예외가 발생한다.")
    void test7() {
        //given
        String password = "pass!";
        //when & then
        Assertions.assertThrows(RuntimeException.class, () -> {
            new Member(UUID.randomUUID(), "username", "email@email.com", password, MemberType.ADMIN);
        });
    }

    @Test
    @DisplayName("이메일은 이메일 형식에 맞지않으면 NotEmailFormatException 예외가 발생한다.")
    void test8() {
        String email = "mail.com";
        Assertions.assertThrows(NotEmailFormatException.class, () -> {
           new Member(UUID.randomUUID(), "username",email,"password!",MemberType.ADMIN);
        });
    }

    @Test
    @DisplayName("도메인이 `gmail`, `naver`, `daum`이 아니면 NotAllowedDomainException 예외가 발생한다.")
    void test9() {
        String email = "mail@gmail.com";
        Assertions.assertThrows(NotAllowedDomainException.class, () -> {
            new Member(UUID.randomUUID(),"username",email,"password!",MemberType.ADMIN);
        });
    }
//- 도메인은 `gmail`, `naver`, `daum`만 가능하다.
//- 회원 유형은 인증 유무에 따라 변하며, 지원자(`APPLICANT`), 회원(`MEMBER`) 중 하나의 정보를 가진다.


}
