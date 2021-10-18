package ua.goit.repository;

import ua.goit.model.Publication;

import java.util.Arrays;

public class InMemoryArrayStorage implements Storage {
    private Publication[] publications;
    private final static int DEFAULT_SIZE = 16;
    private int cursor = 0;
    private final static double LOAD_FACTOR = 3 / 2 + 1;

    public InMemoryArrayStorage() {
        this.publications = new Publication[DEFAULT_SIZE];
    }

    public InMemoryArrayStorage(int size) {
        this.publications = new Publication[size];
    }

    @Override
    public void add(Publication publication) {
        if (publications.length <= cursor) {
            int newSize = (int) (publications.length * LOAD_FACTOR);
            publications = Arrays.copyOf(publications, newSize);
        }
        publications[this.cursor] = publication;
        cursor++;
    }

    @Override
    public void remove(Publication publication) {
        for (int i = 0; i < publications.length; i++) {
            if (publications[i].equals(publication)) {
                publications[i] = null;
            }
        }
    }

    @Override
    public Publication remove(int index) {
        if (index >= publications.length || index < 0) {
            return null;
        }
        Publication publication = publications[index];
        publications[index] = null;
        return publication;
    }

    @Override
    public Publication[] findAll() {
        return Arrays.copyOf(publications, publications.length);
    }
}
