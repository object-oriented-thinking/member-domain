package our.member.member.domain;

import our.member.member.error.ProfanityException;

import java.util.Objects;


public class Username {

    private final String username;

    public Username(String username, ProfanityPolicy profanityClient) {
        if (username.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (profanityClient.containsProfanity(username)) {
            throw new ProfanityException("욕설이 들어가면 안됩니다.");
        }

        if (username.length() > 20 || username.length() < 1) {
            throw new RuntimeException();
        }
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username1 = (Username) o;
        return Objects.equals(username, username1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
