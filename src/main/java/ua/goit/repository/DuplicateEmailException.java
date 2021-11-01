package ua.goit.repository;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String s) {
        super(s);
    }
}
