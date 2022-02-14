package ua.goit.exception;

public class AuthorAlreadyExistException extends RuntimeException{

    public AuthorAlreadyExistException(String message) {
        super (message);
    }

    public AuthorAlreadyExistException() {
        super();
    }
}
