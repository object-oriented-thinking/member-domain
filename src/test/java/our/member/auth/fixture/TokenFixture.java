package our.member.auth.fixture;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TokenFixture {
    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final String TOKEN_EMAIL = "test@test.com";
    public static final String TOKEN_EXPIRED_TIME = LocalDateTime.now().plusMinutes(10).format(LOCAL_DATE_TIME_FORMATTER);
    public static final String NOT_INVALID_TOKEN_EMAIL = "test";
    public static final String NOT_INVALID_TOKEN_EXPIRED_TIME = "11111";
    public static final String TOKEN_VALUE = TOKEN_EMAIL + "---" + TOKEN_EXPIRED_TIME;
    public static final String OUT_OF_DATE_TOKEN_VALUE = TOKEN_EMAIL + "---" + LocalDateTime.now().minusMinutes(30).format(LOCAL_DATE_TIME_FORMATTER);
}
