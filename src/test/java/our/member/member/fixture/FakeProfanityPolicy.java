package our.member.member.fixture;

import our.member.member.domain.ProfanityPolicy;

public class FakeProfanityPolicy implements ProfanityPolicy {
    @Override
    public boolean containsProfanity(String username) {
        return username.contains("fuck");
    }
}
