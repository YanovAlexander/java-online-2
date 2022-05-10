package ua.goit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.goit.model.dao.BookDao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends CrudRepository<BookDao, Integer> {
    List<BookDao> findByName(String name);
    Optional<BookDao> findById(Integer id);

    @Query("SELECT b from BookDao b where b.id IN (?1)")
    Set<BookDao> findByIds (Set<Integer> ids);
}
