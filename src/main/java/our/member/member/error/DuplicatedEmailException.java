package our.member.member.error;

public class DuplicatedEmailException extends RuntimeException{
    public DuplicatedEmailException() {
        super();
    }

    public DuplicatedEmailException(String message) {
        super(message);
    }
}
