package our.member.member.error;

public class NotAllowedDomainException extends RuntimeException{
    public NotAllowedDomainException() {
    }
    public NotAllowedDomainException(String message) {
        super(message);
    }
}
