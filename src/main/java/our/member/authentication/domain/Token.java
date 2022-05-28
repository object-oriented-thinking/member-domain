package our.member.authentication.domain;

import our.member.authentication.error.ExpiredTokenException;
import our.member.authentication.error.NotTokenFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


/**
 * Token은 email과 현재 시간을 기준으로 생성된다.
 * <p>
 * token -> Entity
 * LocalDate email + date => Token
 * decoding = > date < current date => error
 */
public class Token {
    private static final String REGEX = "---";
//    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    private String token;

    public Token(String token){
        String userEmail = getEmail(token);
        String tokenEmail = decodeToken(token).get(0);
        if (!userEmail.equals(tokenEmail)) {
            throw new NotTokenFormatException();
        }

        LocalDateTime createdAt = getLocalDateTime(token);
        LocalDateTime tokenDate = LocalDateTime.parse(decodeToken(token).get(1), formatter);
        if (tokenDate.plusMinutes(30).isBefore(createdAt)) {
            throw new ExpiredTokenException();
        }
        this.token = token;
    }

    public Token creatToken(String email, LocalDateTime now) {
        return new Token(email + REGEX + convertDateToString(now));
    }

    public List<String> decodeToken(String token) {
        String[] strings = token.split(REGEX);
        return Arrays.asList(strings);
    }

    public String getEmail(String token) {
        return token.split(REGEX)[0];
    }

    public LocalDateTime getLocalDateTime(String token){
        return LocalDateTime.parse(token.split(REGEX)[1], formatter);
    }

    public String convertDateToString(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }

}
