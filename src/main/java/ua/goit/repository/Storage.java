package ua.goit.repository;

import ua.goit.model.Publication;

public interface Storage {
    void add(Publication publication);

    void remove(Publication publication);

    Publication remove(int index);

    Publication[] findAll();
}
