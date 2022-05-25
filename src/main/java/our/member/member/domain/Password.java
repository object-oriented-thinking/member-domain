package our.member.member.domain;

import our.member.member.error.NotInputSpecialSymbolException;

import java.util.Objects;

public class Password {

    private final String password;

    public Password(String password, PasswordPolicy passwordPolicy) {

        if (!passwordPolicy.isCorrectPassword(password)) {
            throw new NotInputSpecialSymbolException();
        }

        if (password.length() > 30 || password.length() < 8) {
            throw new RuntimeException();
        }

        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }
}
