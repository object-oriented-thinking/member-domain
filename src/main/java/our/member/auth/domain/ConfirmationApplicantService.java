package our.member.auth.domain;

import java.util.UUID;

public interface ConfirmationApplicantService {
    boolean isApplicant(UUID memberId);
    String findEmailByMemberId(UUID memberId);
}
