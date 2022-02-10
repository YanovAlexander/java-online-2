package ua.goit.service;

import ua.goit.model.converter.JournalConverter;
import ua.goit.model.dao.JournalDao;
import ua.goit.model.dto.JournalDto;
import ua.goit.repository.Repository;

public class JournalService {
    private Repository<JournalDao> repository;
    private JournalConverter converter;

    public JournalService(Repository<JournalDao> repository, JournalConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public void save(JournalDto dto) {
        repository.save(converter.convert(dto));
    }
}
