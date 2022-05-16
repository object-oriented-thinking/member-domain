package our.member.member.error;


public class NonMemberException extends RuntimeException{
    public NonMemberException() {
    }
    public NonMemberException(String message) {
        super(message);
    }

}
