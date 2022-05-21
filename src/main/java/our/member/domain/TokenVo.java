package our.member.domain;


import our.member.error.NotInputSpecialSymbolException;

import java.util.Objects;

/**
 * Token은 비밀번호와 같은 유효성 검사 규칙을 가진다고 가정하였습니다.
 */
public class TokenVo {
    private final String tokenVo;

    public TokenVo(String tokenVo) {
        if (!tokenVo.contains("!")) {
            throw new NotInputSpecialSymbolException();
        }

        if (tokenVo.length() > 30 || tokenVo.length() < 8) {
            throw new RuntimeException();
        }
        this.tokenVo = tokenVo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenVo tokenVo1 = (TokenVo) o;
        return Objects.equals(tokenVo, tokenVo1.tokenVo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenVo);
    }
}
