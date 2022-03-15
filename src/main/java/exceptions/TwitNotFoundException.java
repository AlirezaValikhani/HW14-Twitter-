package exceptions;

public class TwitNotFoundException extends RuntimeException{
    private final String message = "Twit not found";

    @Override
    public String getMessage() {
        return message;
    }

    public TwitNotFoundException() {
    }
}
