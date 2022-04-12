package ua.goit.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    Integer save(T t);

    T findByName(String name);

    Optional<T> findById(int id);

    List<T> findAll();
}
