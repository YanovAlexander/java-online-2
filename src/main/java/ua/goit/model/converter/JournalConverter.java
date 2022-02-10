package ua.goit.model.converter;

import ua.goit.model.dao.JournalDao;
import ua.goit.model.dto.JournalDto;

public class JournalConverter {

    public JournalDao convert(JournalDto dto) {
        JournalDao journalDao = new JournalDao();
            journalDao.setId(dto.getId());
            journalDao.setName(dto.getName());
            journalDao.setNumber(dto.getNumber());
            journalDao.setCountPages(dto.getCountPages());
            journalDao.setPublicationYear(dto.getPublicationYear());
            return journalDao;
    }
}
