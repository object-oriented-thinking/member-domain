package our.member.member.domain;

import our.member.member.error.NotInputSpecialSymbolException;

public class Password {

    private final String password;

    public String getPassword() {
        return password;
    }

    public Password(String password) {

        if (!password.contains("!")) {
            throw new NotInputSpecialSymbolException();
        }

        if (password.length() > 30 || password.length() < 8) {
            throw new RuntimeException();
        }

        this.password = password;
    }
}
