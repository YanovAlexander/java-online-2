package ua.goit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.dao.BookDao;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends CrudRepository<BookDao, Integer> {
    List<BookDao> findByName(String name);
    @Query("SELECT b FROM BookDao b WHERE b.id IN (?1)")
    Set<BookDao> findByIds(Set<Integer> ids);
}
