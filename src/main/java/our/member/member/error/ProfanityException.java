package our.member.member.error;

public class ProfanityException extends RuntimeException {
    public ProfanityException() {
    }

    public ProfanityException(String message) {
        super(message);
    }
}
