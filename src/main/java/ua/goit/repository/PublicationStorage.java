package ua.goit.repository;

import ua.goit.model.Publication;

public interface PublicationStorage {
    void add(Publication publication);

    Publication remove(Publication publication);

    Publication remove(int index);

    Publication[] findAll();
}
