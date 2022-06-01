package ua.goit.exception;

public class BookAlreadyExistsException extends RuntimeException{

    public BookAlreadyExistsException(String message) {
        super (message);
    }

    public BookAlreadyExistsException() {
        super();
    }
}
