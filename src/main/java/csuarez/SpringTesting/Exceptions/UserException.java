package csuarez.SpringTesting.Exceptions;

public class UserException extends RuntimeException {

    private UserException(String message) {
        super(message);
    }

    public static UserException usernameTaken(String username) {
        return new UserException("Username already taken: " + username);
    }

    public static UserException notFound(String username) {
        return new UserException("User not found: " + username);
    }

    public static UserException refreshTokenExpired() {
        return new UserException("Refresh token has expired.");
    }
}