package ua.goit.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Repository<T> {

    Integer save(T t);

    T findByName(String name);

    Optional<T> findById(Integer id);

    List<T> findAll();

    List<T> findByIds(Set<Integer> id);
}
