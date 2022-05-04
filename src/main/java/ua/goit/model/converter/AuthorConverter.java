package ua.goit.model.converter;

import ua.goit.model.dao.AuthorDao;
import ua.goit.model.dto.AuthorDto;

public class AuthorConverter implements Converter<AuthorDao, AuthorDto>{

    public AuthorConverter() {
    }

    @Override
    public AuthorDto from(AuthorDao type) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(type.getId());
        authorDto.setFirstName(type.getFirstName());
        authorDto.setLastName(type.getLastName());
        authorDto.setEmail(type.getEmail());
        return authorDto;
    }

    @Override
    public AuthorDao to(AuthorDto type) {
        AuthorDao authorDao = new AuthorDao();
        authorDao.setId(type.getId());
        authorDao.setFirstName(type.getFirstName());
        authorDao.setLastName(type.getLastName());
        authorDao.setEmail(type.getEmail());
        return authorDao;
    }
}
