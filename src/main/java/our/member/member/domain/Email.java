package our.member.member.domain;

import our.member.member.error.NotAllowedDomainException;
import our.member.member.error.NotEmailFormatException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Email {
    private final String email;

    public Email(String email) {
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
        if (!domain.equals("gmail") && !domain.equals("naver") && !domain.equals("daum")) {
            throw new NotAllowedDomainException();
        }

        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
