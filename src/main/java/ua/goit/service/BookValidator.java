package ua.goit.service;

import ua.goit.model.ErrorMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

public class BookValidator {

    public BookValidator() {
    }

    public ErrorMessage validateCreateBook(HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage();
        List<String> errors = new ArrayList<>();

        try {
            Integer.parseInt(request.getParameter("countPages"));
        } catch (Exception e) {
            errors.add("Count pages number is invalid");
        }

        String name = request.getParameter("bookName");
        if (name.isBlank()) {
            errors.add("Book title can not be empty");
        }

        try {
            Set<Integer> authorIds = Arrays.stream(request.getParameterValues("authorId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());

            if (authorIds.isEmpty()) {
                errors.add("Authors must be set");
            }
        } catch (Exception e) {
            errors.add("Authors must be set");
        }

        errorMessage.setErrors(errors);

        return errorMessage;
    }
}
