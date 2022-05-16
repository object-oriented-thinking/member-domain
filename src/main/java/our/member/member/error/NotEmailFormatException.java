package our.member.member.error;

public class NotEmailFormatException extends RuntimeException{
    public NotEmailFormatException(){}
    public NotEmailFormatException(String message) {
        super(message);
    }
}
