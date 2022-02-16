package ua.goit.model.converter;

import ua.goit.model.dao.BookDao;
import ua.goit.model.dto.BookDto;

public class BookConverter implements Converter<BookDao, BookDto>{

    @Override
    public BookDto from(BookDao type) {
        BookDto dto = new BookDto();
        dto.setName(type.getName());
        dto.setCountPages(type.getCountPages());
        return dto;
    }

    @Override
    public BookDao to(BookDto type) {
        BookDao dao = new BookDao();
        dao.setName(type.getName());
        dao.setCountPages(type.getCountPages());
        return dao;
    }
}
