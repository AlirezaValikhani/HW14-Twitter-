package exceptions;

public class UserNotFoundException extends RuntimeException{
    private final String message = "User not found";

    @Override
    public String getMessage() {
        return message;
    }

    public UserNotFoundException() {
    }
}
