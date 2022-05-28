package our.member;

import our.member.authentication.domain.AuthenticationDetails;
import our.member.authentication.domain.TokenState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static java.time.LocalDateTime.*;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationDetailsTest {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    @DisplayName("토큰과 유저 식별와 토큰상태가 들어간다.")
    void testAuthenticationDetails(){
        String userEmail = "bora@nate.com";
        String createdAt = now().format(formatter);
        String token = userEmail + "---" + createdAt;

        AuthenticationDetails auth = new AuthenticationDetails(UUID.randomUUID(), token, UUID.randomUUID(), TokenState.VALID);
        assertDoesNotThrow(() -> auth);
    }

}
