package ua.goit.repository;

import java.util.Optional;

public interface Repository<T> {

    void save(T t);

    T findByName(String name);

    Optional<T> findById(int id);
}
