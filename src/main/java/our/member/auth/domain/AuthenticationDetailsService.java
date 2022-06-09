package our.member.auth.domain;

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
        if (authenticationDetailsRepository.findByMemberId(memberId).isPresent()) {
            if (!confirmationApplicantService.isApplicant(memberId)) {
                throw new NotAllowedException();
            }

            Token token = new Token();
            AuthenticationDetails authenticationDetails = authenticationDetailsRepository.findByMemberId(memberId).get();
            AuthenticationDetails updateAuthenticationDetails = new AuthenticationDetails(authenticationDetails, token);

            // UPDATE
            authenticationDetailsRepository.save(updateAuthenticationDetails);
            return notificationService.sendToMail(confirmationApplicantService.findEmailByMemberId(memberId), token);
        }

        Token token = new Token();
        AuthenticationDetails authenticationDetails = new AuthenticationDetails(null, memberId, token);

        //CREATE
        authenticationDetailsRepository.save(authenticationDetails);
        return notificationService.sendToMail(confirmationApplicantService.findEmailByMemberId(memberId), token);
    }
}
