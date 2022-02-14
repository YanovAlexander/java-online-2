package ua.goit.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(String message) {
            super(message);
        }

    public AuthorNotFoundException() {
            super();
        }

}
