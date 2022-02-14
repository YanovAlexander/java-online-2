package ua.goit.model.converter;

import ua.goit.model.dao.JournalDao;
import ua.goit.model.dto.JournalDto;

public class JournalDtoToJournalDaoConverter implements Converter<JournalDto, JournalDao> {

    @Override
    public JournalDao from(JournalDto dto) {
        JournalDao journalDao = new JournalDao();
            journalDao.setId(dto.getId());
            journalDao.setName(dto.getName());
            journalDao.setNumber(dto.getNumber());
            journalDao.setCountPages(dto.getCountPages());
            journalDao.setPublicationYear(dto.getPublicationYear());
            return journalDao;
    }

    @Override
    public JournalDto to(JournalDao type) {
        JournalDto journalDto = new JournalDto();
        journalDto.setId(type.getId());
        journalDto.setName(type.getName());
        journalDto.setNumber(type.getNumber());
        journalDto.setCountPages(type.getCountPages());
        journalDto.setPublicationYear(type.getPublicationYear());
        return journalDto;
    }
}
