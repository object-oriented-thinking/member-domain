package our.member.auth.domain;

import our.member.auth.error.NotAllowedException;

import java.util.UUID;

public class AuthenticationDetailsService {

    private final ConfirmationApplicantService confirmationApplicantService;
    private final NotificationService notificationService;
    private final AuthenticationDetailsRepository authenticationDetailsRepository;

    public AuthenticationDetailsService(ConfirmationApplicantService confirmationApplicantService, NotificationService notificationService, AuthenticationDetailsRepository authenticationDetailsRepository) {
        this.confirmationApplicantService = confirmationApplicantService;
        this.notificationService = notificationService;
        this.authenticationDetailsRepository = authenticationDetailsRepository;
    }

    public boolean requestAuthentication(UUID memberId) {
        String email = confirmationApplicantService.findEmailByMemberId(memberId);

        if (authenticationDetailsRepository.findByMemberId(memberId).isPresent()) {
            if (!confirmationApplicantService.isApplicant(memberId)) {
                throw new NotAllowedException();
            }

            Token token = Token.createToken(email);
            AuthenticationDetails authenticationDetails = authenticationDetailsRepository.findByMemberId(memberId).get();
            AuthenticationDetails updateAuthenticationDetails = new AuthenticationDetails(authenticationDetails, token);

            authenticationDetailsRepository.save(updateAuthenticationDetails);
            return notificationService.sendToMail(email, token);
        }

        Token token = Token.createToken(email);
        AuthenticationDetails authenticationDetails = new AuthenticationDetails(null, memberId, token);

        authenticationDetailsRepository.save(authenticationDetails);
        return notificationService.sendToMail(email, token);
    }
}
