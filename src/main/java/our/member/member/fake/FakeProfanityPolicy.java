package our.member.member.fake;

import org.springframework.stereotype.Component;
import our.member.member.domain.ProfanityPolicy;

@Component
public class FakeProfanityPolicy implements ProfanityPolicy {
    @Override
    public boolean containsProfanity(String username) {
        return username.contains("fuck");
    }
}
