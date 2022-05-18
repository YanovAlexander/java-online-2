package ua.goit.exception;

import javax.validation.constraints.Email;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(@Email String s) {
        super(s);
    }
}
