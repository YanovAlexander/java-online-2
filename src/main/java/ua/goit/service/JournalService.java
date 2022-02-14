package ua.goit.service;

import ua.goit.model.converter.JournalDtoToJournalDaoConverter;
import ua.goit.model.dto.JournalDto;
import ua.goit.repository.JournalRepository;

public class JournalService {
    private JournalRepository repository;
    private JournalDtoToJournalDaoConverter converter;

    public JournalService(JournalRepository repository, JournalDtoToJournalDaoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public void save(JournalDto dto) {
        repository.save(converter.from(dto));
    }
}
