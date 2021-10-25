package ua.goit.repository;

import ua.goit.model.Publication;

import java.util.LinkedList;
import java.util.List;

public class InMemoryListStorage implements PublicationStorage {
    private List<Publication> publications = new LinkedList<>();

    public InMemoryListStorage() {
    }

    @Override
    public void add(Publication publication) {
        publications.add(publication);
    }

    @Override
    public Publication remove(Publication publication) {
        return publications.remove(publication) ? publication : null;
    }

    @Override
    public Publication remove(int index) {
        if (index >= publications.size() || index < 0) {
            return null;
        }
        return publications.remove(index);
    }

    @Override
    public Publication[] findAll() {
        return publications.toArray(new Publication[publications.size()]);
    }
}

