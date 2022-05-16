package ua.goit.controller.propertyEditors;

import ua.goit.model.dto.AuthorDto;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

public class AuthorDtoPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (Objects.isNull(text) || text.isBlank()) {
            setValue(null);
            return;
        }
        final String[] split = text.split(",");
        AuthorDto dto = new AuthorDto();
        dto.setId(Integer.parseInt(split[0].trim()));
        dto.setFirstName(split[1].trim());
        dto.setLastName(split[2].trim());
        dto.setEmail(split[3].trim());
        setValue(dto);
    }
}
