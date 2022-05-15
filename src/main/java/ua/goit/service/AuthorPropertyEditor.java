package ua.goit.service;

import ua.goit.model.dto.AuthorDto;

import java.beans.PropertyEditorSupport;

public class AuthorPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] parts = text.split(",");
        AuthorDto author = new AuthorDto();
        author.setId(Integer.parseInt(parts[0].trim()));
        author.setFirstName(parts[1].trim());
        author.setLastName(parts[2].trim());
        author.setEmail(parts[3].trim());
        setValue(author);
    }
}
