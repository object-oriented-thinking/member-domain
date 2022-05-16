package our.member.member.domain;

import our.member.member.error.*;
import our.member.member.error.NotAllowedDomainException;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Member {
    private Username username;
    public Member(UUID id, String username, String email, String password, MemberType memberType) {
        if (!password.contains("!")) {
            throw new NotInputSpecialSymbolException();
        }

        if (password.length() > 30 || password.length() < 8) {
            throw new RuntimeException();
        }

        //Regular Expression
        String regx = "^(.+)@(.+)$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regx);
        //Create instance of matcher
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new NotEmailFormatException();
        }

        String domain = email.substring(email.indexOf("@") + 1, email.indexOf("."));
        if (domain != "gmail" && domain != "naver" && domain != "daum") {
            throw new NotAllowedDomainException();
        }

        if(memberType != MemberType.ADMIN && memberType != MemberType.APPLICANT && memberType != MemberType.MEMBER){
            throw new NonMemberException();
        }
        this.username = new Username(username);
    }
}
