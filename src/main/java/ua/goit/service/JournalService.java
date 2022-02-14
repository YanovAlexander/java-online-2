package ua.goit.service;

import ua.goit.model.converter.JournalConverter;
import ua.goit.model.dto.JournalDto;
import ua.goit.repository.JournalRepository;

public class JournalService {
    private JournalRepository repository;
    private JournalConverter converter;

    public JournalService(JournalRepository repository, JournalConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public void save(JournalDto dto) {
        repository.save(converter.convert(dto));
    }
}
