package our.member.auth.domain;

import java.util.UUID;

public class AuthenticationDetails {

    private final UUID authenticationId;
    private final UUID memberId;
    private final Token token;

    public AuthenticationDetails(UUID authenticationId, UUID memberId, Token token) {
        this.authenticationId = authenticationId;
        this.memberId = memberId;
        this.token = token;
    }

    public AuthenticationDetails(AuthenticationDetails authenticationDetails, Token token) {
        this(authenticationDetails.authenticationId, authenticationDetails.memberId, token);
    }

    public UUID getAuthenticationId() {
        return authenticationId;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public Token getToken() {
        return token;
    }

}
