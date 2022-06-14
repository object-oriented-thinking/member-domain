package our.member.auth.domain;

import our.member.auth.error.InvalidTokenException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.parse;

public class Token {
    private static final String EMAIL_REGEX = "^[_a-z\\d-]+(.[_a-z\\d-]+)*@(?:\\w+\\.)+\\w+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final int EMAIL_INDEX = 0;
    private static final int EXPIRED_TIME_INDEX = 1;
    private static final String REGEX = "---";
    private static final int EXPIRED_TIME = 30;

    private final String token;

    public Token(String token) {

        if (token.isBlank()) {
            throw new IllegalArgumentException("토큰 값이 공백일 수 없습니다.");
        }

        if (!(token.contains(REGEX) && isValidEmailInToken(token) && isValidExpiredTimeInToken(token))) {
            throw new IllegalArgumentException("유효하지 않은 토큰 값입니다.");
        }

        if (!isValidToken(token)) {
            throw new InvalidTokenException("유효하지 않은 토큰 값입니다.");
        }

        this.token = token;
    }

    public static Token createToken(String email) {
        return new Token(email + REGEX + now().plusMinutes(Token.EXPIRED_TIME).format(FORMATTER));
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return this.token.split(REGEX)[EMAIL_INDEX];
    }

    public LocalDateTime getExpiredTime() {
        String expiredTime = this.token.split(REGEX)[EXPIRED_TIME_INDEX];
        return parse(expiredTime, FORMATTER);
    }

    private boolean isValidToken(String token) {
        String expiredTime = token.split(REGEX)[EXPIRED_TIME_INDEX];
        return parse(expiredTime, FORMATTER).isAfter(now());
    }

    private boolean isValidEmailInToken(String token) {
        Matcher m = EMAIL_PATTERN.matcher(token.split(REGEX)[EMAIL_INDEX]);
        return m.matches();
    }

    private boolean isValidExpiredTimeInToken(String token) {
        try {
            String expiredTime = token.split(REGEX)[EXPIRED_TIME_INDEX];
            parse(expiredTime, FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
