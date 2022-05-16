package ua.goit.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.model.dao.BookDao;
import ua.goit.model.dto.BookDto;

import java.util.stream.Collectors;

@Service
public class BookConverter implements Converter<BookDao, BookDto>{
    private final AuthorConverter authorConverter;

    @Autowired
    public BookConverter(AuthorConverter authorConverter) {
        this.authorConverter = authorConverter;
    }

    @Override
    public BookDto from(BookDao type) {
        BookDto dto = new BookDto();
        dto.setId(type.getId());
        dto.setName(type.getName());
        dto.setCountPages(type.getCountPages());
        dto.setAuthors(type.getAuthors().stream()
                .map(authorConverter::from)
                .collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public BookDao to(BookDto type) {
        BookDao dao = new BookDao();
        dao.setId(type.getId());
        dao.setName(type.getName());
        dao.setCountPages(type.getCountPages());
        dao.setAuthors(type.getAuthors().stream()
                .map(authorConverter::to)
                .collect(Collectors.toSet()));
        return dao;
    }
}
