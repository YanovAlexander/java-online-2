package ua.goit.repository;

import ua.goit.model.Author;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InMemoryAuthorStorage implements AuthorStorage {
    private Set<Author> authors = new HashSet<>();
    private int sequence = 0;

    @Override
    public void add(Author author) {
        ++sequence;
        author.setId(sequence);
        authors.add(author);
    }

    @Override
    public Author remove(Author author) {
        return authors.remove(author) ? author : null;
    }

    @Override
    public Author get(long id) {
        return authors.stream()
                .filter(aut -> aut.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Author author) {
        Optional<Author> first = authors.stream()
                .filter(aut -> aut.getId() == author.getId())
                .findFirst();
        first.ifPresent(aut -> {authors.remove(aut);
            authors.add(author);
        });
    }
}
